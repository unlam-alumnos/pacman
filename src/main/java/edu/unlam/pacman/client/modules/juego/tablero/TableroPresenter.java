package edu.unlam.pacman.client.modules.juego.tablero;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.async.Callback;
import edu.unlam.pacman.shared.comunication.bus.async.Request;
import edu.unlam.pacman.shared.comunication.bus.events.HunterEvent;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;
import edu.unlam.pacman.shared.util.PropertiesUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class TableroPresenter extends Presenter<TableroView> implements TableroView.MyView {
    private Casillero[][] casilleros;
    private Timer cronometro;
    private Integer duracion = Integer.parseInt(PropertiesUtils.pref().get(SharedConstants.GAME_LENGTH, null));
    private int contadorFrutas=0;
    public TableroPresenter() {
        super(new TableroView());
        construirTablero();
        cronometro.start();
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
                    getView().dibujarTimer(casillero.getOrigen(), duracion);
                } else if (Casillero.Tipo.FRUTA_ESPECIAL.equals(tipo)) {
                    Coordenada coordenada = new Coordenada(casillero.getOrigen().getX() + casillero.getAncho() / 2, casillero.getOrigen().getY() + casillero.getAlto() / 2);
                    getView().dibujarFrutaEspecial(casillero.getOrigen(), casillero.getAncho(), casillero.getAlto());

                }
            }
        }
    }

    /**
     * Verifica la validez del movimiento pedido y responde solo si es válido
     *
     * @param request
     */
    @Subscribe
    @AllowConcurrentEvents
    public void handleMoveEventRequest(Request<MoveEvent> request) {
        MoveEvent moveEvent = request.getEvent();
        int i = 0;
        int x = 0;
        int y = 0;

        for (Casillero[] fila : casilleros) {
            int j = 0;
            for (Casillero casillero : fila) {
                Coordenada proyeccion = new Coordenada(moveEvent.getOrigen().getX() + casillero.getAncho() / 2, moveEvent.getOrigen().getY() + casillero.getAlto() / 2);
                if (casillero.contains(proyeccion)) {
                    Casillero next = null;
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

                        if (Casillero.Tipo.FRUTA.equals(next.getTipo())) {
                            eventBus.post(new Callback<>(moveEvent));
                            if (moveEvent.getPersonajeType().equals("pacman")){
                                casilleros[x][y].setTipo(Casillero.Tipo.PISO);
                                contadorFrutas--;
                            }
                        }else if (Casillero.Tipo.FRUTA_ESPECIAL.equals(next.getTipo())) {
                            eventBus.post(new Callback<>(moveEvent));
                            eventBus.post(new HunterEvent(moveEvent.getSubject()));
                            casilleros[x][y].setTipo(Casillero.Tipo.PISO);
                            contadorFrutas--;
                        }else if(Casillero.Tipo.PISO.equals(next.getTipo())) {
                            eventBus.post(new Callback<>(moveEvent));
                        }
                        if (contadorFrutas==0) {
                            eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
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
     * Crea los casilleros del tablero a partir de la matriz dada
     */
    private void construirTablero() {
        int[][] board = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, -1, 0, 0, 2, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        this.casilleros = new Casillero[board.length][board[0].length];

        int x = 0;
        int y = 0;
        int size = 50;
        for (int i = 0; i < board.length; i++) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.PARED);
                } else if (row[j] == 0) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA);
                    contadorFrutas++;
                } else if (row[j] == -1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.CRONOMETRO);
                } else if (row[j] == 2) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.FRUTA_ESPECIAL);
                    contadorFrutas++;
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
                duracion--;
                if (duracion == 0){
                    cronometro.stop();
                    eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
                }
            }
        });
    }
}
