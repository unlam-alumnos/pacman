package edu.unlam.pacman.client.modules.juego.tablero;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.async.DirectionEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.DirectionEventRequest;
import edu.unlam.pacman.shared.comunication.bus.async.MoveEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.MoveEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.HunterEvent;
import edu.unlam.pacman.shared.comunication.bus.events.PaintEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;
import edu.unlam.pacman.shared.util.PropertiesUtils;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class TableroPresenter extends Presenter<TableroView> implements TableroView.MyView {
    private final int duracion = Integer.parseInt(PropertiesUtils.pref().get(SharedConstants.GAME_LENGTH, null));

    private List<Coordenada> toRevive;
    private Set<Personaje> personajes;
    private Casillero[][] casilleros;

    private Timer cronometro;
    private Cronometro timekeeper = new Cronometro(duracion);

    private int contadorFrutas = 0;

    public TableroPresenter() {
        super(new TableroView());
        construirTablero();
        cronometro.start();
        this.personajes = new HashSet<>();
    }

    @Override
    public void paint() {
        for (Casillero[] fila : casilleros) {
            for (Casillero casillero : fila) {
                Casillero.Tipo tipo = casillero.getTipo();
                if (Casillero.Tipo.PARED.equals(tipo)) {
                    getView().dibujarPared(casillero.getOrigen(), casillero.getAncho(), casillero.getAlto());
                } else if (Casillero.Tipo.FRUTA.equals(tipo)) {
                    Coordenada coordenada = new Coordenada(casillero.getOrigen().getX() + casillero.getAncho() / 2, casillero.getOrigen().getY() + casillero.getAlto() / 2);
                    getView().dibujarFruta(coordenada, 2);

                } else if (Casillero.Tipo.PISO.equals(tipo)) {
                    Coordenada coordenada = new Coordenada(casillero.getOrigen().getX() + casillero.getAncho() / 2, casillero.getOrigen().getY() + casillero.getAlto() / 2);
                    getView().dibujarPiso(coordenada);
                } else if (Casillero.Tipo.CRONOMETRO.equals(tipo)) {
                    getView().dibujarCronometro(casillero.getOrigen(), timekeeper.getValueString());

                } else if (Casillero.Tipo.BLOCK.equals(tipo)) {
                    Coordenada coordenada = new Coordenada(casillero.getOrigen().getX() + casillero.getAncho() / 2, casillero.getOrigen().getY() + casillero.getAlto() / 2);
                    getView().dibujarPiso(coordenada);
                } else if (Casillero.Tipo.FRUTA_ESPECIAL.equals(tipo)) {
                    Coordenada coordenada = new Coordenada(casillero.getOrigen().getX() + casillero.getAncho() / 2, casillero.getOrigen().getY() + casillero.getAlto() / 2);
                    getView().dibujarFrutaEspecial(casillero.getOrigen(), casillero.getAncho(), casillero.getAlto());
                }
            }
        }
    }

    /**
     * Verifica la validez del cambio de direccion y responde solo si es válido
     */
    @Subscribe
    @AllowConcurrentEvents
    public void handleDirectionEventRequest(DirectionEventRequest directionEvent){
        int i = 0;
        int x = 0;
        int y = 0;

        for (Casillero[] fila : casilleros) {
            int j = 0;
            for (Casillero casillero : fila) {
                Coordenada proyeccion = new Coordenada(directionEvent.getOrigen().getX() + casillero.getAncho() / 2, directionEvent.getOrigen().getY() + casillero.getAlto() / 2);
                if (casillero.contains(proyeccion)) {
                    Casillero next = null;
                    Direction direction = directionEvent.getDireccion();
                    try {

                        switch (direction){
                            case RIGHT:
                                x = i;
                                y = j + 1;
                                break;
                            case LEFT:
                                x = i;
                                y = j - 1;
                                break;
                            case UP:
                                x = i - 1;
                                y = j;
                                break;
                            case DOWN:
                                x = i + 1;
                                y = j;
                                break;
                            default:
                                x = i;
                                y = j;
                                break;
                        }
                        next = casilleros[x][y];
                        if (!Casillero.Tipo.PARED.equals(next.getTipo()) && !Casillero.Tipo.CRONOMETRO.equals(next.getTipo())) {
                            eventBus.post(new DirectionEventCallback(directionEvent.getSubject(), directionEvent.getOrigen(), directionEvent.getDireccion()));
                        }
                    } catch (Exception e) {
                        System.out.println("Se pasó!");
                    }
                }
                j++;
            }
            i++;
        }
    }

    /**
     * Verifica la validez del movimiento pedido y responde solo si es válido
     */
    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveEventRequest(MoveEventRequest moveEvent) {
        int i = 0;
        int x = 0;
        int y = 0;

        for (Casillero[] fila : casilleros) {
            int j = 0;
            for (Casillero casillero : fila) {
                Coordenada proyeccion = new Coordenada(moveEvent.getOrigen().getX() + casillero.getAncho() / 2, moveEvent.getOrigen().getY() + casillero.getAlto() / 2);
                if (casillero.contains(proyeccion)) {
                    Casillero next;
                    Direction direction = moveEvent.getDireccion();
                    try {

                        switch (direction){
                            case RIGHT:
                                x = i;
                                y = j + 1;
                                break;
                            case LEFT:
                                x = i;
                                y = j - 1;
                                break;
                            case UP:
                                x = i - 1;
                                y = j;
                                break;
                            case DOWN:
                                x = i + 1;
                                y = j;
                                break;
                            default:
                                x = i;
                                y = j;
                                break;
                        }

                        next = casilleros[x][y];

                        MoveEventCallback callback = new MoveEventCallback(moveEvent.getSubject(), moveEvent.getOrigen(), moveEvent.getDireccion(), moveEvent.getPersonajeType(), moveEvent.getPersonaje());

                        if (Casillero.Tipo.FRUTA.equals(next.getTipo())) {
                            eventBus.post(callback);
                            if (moveEvent.getPersonajeType().equals("pacman")){
                                casilleros[x][y].setTipo(Casillero.Tipo.PISO);
                                contadorFrutas--;
                            }
                        }else if (Casillero.Tipo.FRUTA_ESPECIAL.equals(next.getTipo())) {
                            eventBus.post(callback);
                            eventBus.post(new HunterEvent(moveEvent.getSubject()));
                            casilleros[x][y].setTipo(Casillero.Tipo.PISO);
                            contadorFrutas--;
                        }else if(Casillero.Tipo.PISO.equals(next.getTipo())) {
                            eventBus.post(callback);
                        }
                        if (contadorFrutas==0) {
                            eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
                        }

                        checkCollision(moveEvent.getPersonaje());

                    } catch (Exception e) {
                        System.out.println("Se pasó!");
                    }
                }
                j++;
            }
            i++;
        }
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handlePaintEvent(PaintEvent event) {
        personajes.add(event.getPersonaje());
    }

    private void checkCollision(Personaje personaje) {
        for (Personaje pj : personajes) {
            if (!pj.equals(personaje)) {
                if (pj.getX() == personaje.getX() && pj.getY() == personaje.getY()) {
                    System.out.println(pj.getId() + " chocó con " + personaje.getId() + " en (" + pj.getX() + "," + pj.getY() + ")");

                    /**
                     * Identifico el Tipo de Personaje que corresponde a cada participe de la colision
                     */
                    System.out.println(pj.getTipoPersonaje() + " chocó con " + personaje.getTipoPersonaje() + " en (" + pj.getX() + "," + pj.getY() + ")");

                    if (pj.getStatus().equals(Status.HUNTER) && personaje.getStatus().equals(Status.VICTIM)){
                        System.out.println(pj.getTipoPersonaje() + " se comió a " + personaje.getTipoPersonaje());
                        personaje.dead(siteToRevive(personaje));
                    } else if (pj.getStatus().equals(Status.VICTIM) && personaje.getStatus().equals(Status.HUNTER)){
                        System.out.println(personaje.getTipoPersonaje() + " se comió a " + pj.getTipoPersonaje());
                        pj.dead(siteToRevive(pj));
                    } else if (pj.getTipoPersonaje().equals(personaje.getTipoPersonaje())){
                        // No puede haber 2 pacman en la partida, entonces chocaron 2 fantasmas
                        pj.setStatus(Status.BLOCK);
                        personaje.setStatus(Status.BLOCK);
                    } else if (!pj.getTipoPersonaje().equals(personaje.getTipoPersonaje())){
                        // Choco 1 pacman con algun fantasma, sin estar en modo cazador
                        if (pj.getTipoPersonaje().equals("Pacman")){
                            pj.dead(siteToRevive(pj));
                        }else{
                            personaje.dead(siteToRevive(personaje));
                        }
                    }
                }
            }
        }
    }

    public Coordenada siteToRevive(Personaje victim){
        double distanciaProm = 0;
        boolean first = true;
        Coordenada aux = new Coordenada(25, 25);
        double max = 0;

        for(Coordenada posible : toRevive){
            for (Personaje personaje : personajes){
                if (!personaje.equals(victim)){
                    distanciaProm += posible.distancia(new Coordenada(personaje.getX(), personaje.getY()));
                }
            }
            distanciaProm = distanciaProm/(personajes.size()-1);
            if (first){
                aux = posible;
                max = distanciaProm;
                first = false;
            }else{
                if (max < distanciaProm){
                    max = distanciaProm;
                    aux = posible;
                }
            }
            distanciaProm = 0;
        }
        System.out.println("Revive en : " + aux.toString());
        return aux;

    }

    /**
     * Crea los casilleros del tablero a partir de la matriz dada
     */
    private void construirTablero() {
        int[][] board = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1,-1,-2,-2,-2, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1,-2,-2,-2,-2, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        this.casilleros = new Casillero[board.length][board[0].length];

        int x = 0;
        int y = 0;
        int size = 25;
        this.toRevive = new ArrayList<Coordenada>();

        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.PARED);
                } else if (row[j] == 0) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA);
                    contadorFrutas++;
                    toRevive.add(new Coordenada(x, y));
                } else if (row[j] == -1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.CRONOMETRO);
                } else if (row[j] == -2) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.BLOCK);
                }  else if (row[j] == 2) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA_ESPECIAL);
                    contadorFrutas++;
                    toRevive.add(new Coordenada(x, y));
                }
                x += size;
            }
            x = 0;
            y += size;
        }

        cronometro = new Timer(1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                timekeeper.less();
                if (timekeeper.getValue() == 0){
                    cronometro.stop();
                    eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
                }
            }
        });
    }
}
