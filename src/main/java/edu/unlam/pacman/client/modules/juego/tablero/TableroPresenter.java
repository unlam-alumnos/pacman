package edu.unlam.pacman.client.modules.juego.tablero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.juego.personaje.Personaje;
import edu.unlam.pacman.client.modules.juego.personaje.pacman.Pacman;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.async.DirectionEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.DirectionEventRequest;
import edu.unlam.pacman.shared.comunication.bus.async.MoveEventCallback;
import edu.unlam.pacman.shared.comunication.bus.async.MoveEventRequest;
import edu.unlam.pacman.shared.comunication.bus.events.BlockEvent;
import edu.unlam.pacman.shared.comunication.bus.events.DeadEvent;
import edu.unlam.pacman.shared.comunication.bus.events.HunterEvent;
import edu.unlam.pacman.shared.comunication.bus.events.KillEvent;
import edu.unlam.pacman.shared.comunication.bus.events.PaintEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScoreEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.model.Status;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class TableroPresenter extends Presenter<TableroView> implements TableroView.MyView {

    private List<Coordenada> camino;
    private Set<Personaje> personajes;
    private Casillero[][] casilleros;

    private int contadorFrutas = 0;

    public TableroPresenter() {
        super(new TableroView());
        construirTablero();
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
                }  else if (Casillero.Tipo.CRONOMETRO.equals(tipo)) {
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
        Casillero actual = null;

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
                        actual = casilleros[i][j];

                        MoveEventCallback callback = new MoveEventCallback(moveEvent.getSubject(), moveEvent.getOrigen(), moveEvent.getDireccion(), moveEvent.getPersonajeType(), moveEvent.getPersonaje());

                        if (Casillero.Tipo.FRUTA.equals(actual.getTipo())) {
                            if (!Casillero.Tipo.PARED.equals(next.getTipo()))
                                eventBus.post(callback);
                            if (moveEvent.getPersonaje() instanceof Pacman){
                                casilleros[i][j].setTipo(Casillero.Tipo.PISO);
                                contadorFrutas--;
                            }
                        }else if (Casillero.Tipo.FRUTA_ESPECIAL.equals(actual.getTipo())) {
                            if (!Casillero.Tipo.PARED.equals(next.getTipo()))
                                eventBus.post(callback);
                            eventBus.post(new HunterEvent(moveEvent.getSubject()));
                            casilleros[i][j].setTipo(Casillero.Tipo.PISO);
                            contadorFrutas--;
                        }else if(Casillero.Tipo.PISO.equals(actual.getTipo())) {
                            if (!Casillero.Tipo.PARED.equals(next.getTipo()))
                                eventBus.post(callback);
                        }

                        //NO HAY MAS FRUTAS EN EL MAPA
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
                    /**
                     * Identifico el Tipo de Personaje que corresponde a cada participe de la colision
                     */

                    if (pj.getStatus().equals(Status.HUNTER) && personaje.getStatus().equals(Status.VICTIM)){
                        eventBus.post(new DeadEvent(personaje.getJugador().getUsername(), dondeRevivir(personaje)));
                        eventBus.post(new KillEvent(pj.getJugador().getUsername()));
                    } else if (pj.getStatus().equals(Status.VICTIM) && personaje.getStatus().equals(Status.HUNTER)){
                        eventBus.post(new DeadEvent(pj.getJugador().getUsername(), dondeRevivir(pj)));
                        eventBus.post(new KillEvent(personaje.getJugador().getUsername()));
                    } else if (pj.getTipoPersonaje().equals(personaje.getTipoPersonaje())){
                        // No puede haber 2 pacman en la partida, entonces chocaron 2 fantasmas

                        eventBus.post(new BlockEvent(personaje.getJugador().getUsername(), false, Status.BLOCK));
                        eventBus.post(new BlockEvent(pj.getJugador().getUsername(), false, Status.BLOCK));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        eventBus.post(new BlockEvent(personaje.getJugador().getUsername(), true, Status.NORMAL));
                        eventBus.post(new BlockEvent(pj.getJugador().getUsername(), true, Status.NORMAL));
                    } else if (!pj.getTipoPersonaje().equals(personaje.getTipoPersonaje())){
                        // Choco 1 pacman con algun fantasma, sin estar en modo cazador

                        if (pj.getTipoPersonaje().equals("Pacman")){
                            eventBus.post(new DeadEvent(pj.getJugador().getUsername(), dondeRevivir(pj)));
                            eventBus.post(new KillEvent(personaje.getJugador().getUsername()));
                        }else{
                            eventBus.post(new DeadEvent(personaje.getJugador().getUsername(), dondeRevivir(personaje)));
                            eventBus.post(new KillEvent(pj.getJugador().getUsername()));
                        }
                    }
                }
            }
        }
        eventBus.post(new ScoreEvent(personajes));
    }

    public Coordenada dondeRevivir(Personaje victim){
        double distanciaProm = 0;
        boolean first = true;
        Coordenada aux = new Coordenada(25, 25);
        double max = 0;

        for(Coordenada posible : camino){
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
                {1, 0, 1, 1, 0, 1, 0, 1,-1,-1,-1,-1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1,-1,-1,-1,-1, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        this.casilleros = new Casillero[board.length][board[0].length];

        int x = 0;
        int y = 0;
        int size = 25;
        this.camino = new ArrayList<Coordenada>();

        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.PARED);
                } else if (row[j] == 0) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA);
                    contadorFrutas++;
                    camino.add(new Coordenada(x, y));
                } else if (row[j] == -1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.CRONOMETRO);
                }  else if (row[j] == 2) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA_ESPECIAL);
                    contadorFrutas++;
                    camino.add(new Coordenada(x, y));
                }
                x += size;
            }
            x = 0;
            y += size;
        }
    }
}
