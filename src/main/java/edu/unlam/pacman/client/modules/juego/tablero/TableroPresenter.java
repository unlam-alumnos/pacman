package edu.unlam.pacman.client.modules.juego.tablero;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.comunication.bus.async.Callback;
import edu.unlam.pacman.shared.comunication.bus.async.Request;
import edu.unlam.pacman.shared.comunication.bus.events.MoveEvent;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.model.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class TableroPresenter extends Presenter<TableroView> implements TableroView.MyView {
    private Casillero[][] casilleros;
    private Timer timer;
    private Integer tiempo = 60;

    public TableroPresenter() {
        super(new TableroView());
        construirTablero();
        timer.start();
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
                    getView().dibujarTimer(casillero.getOrigen(), tiempo);
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
                    Casillero siguiente = null;
                    Direction direccion = moveEvent.getDireccion();
                    try {
                        if (Direction.UP.equals(direccion)) {
                            x = i - 1;
                            y = j;
                        } else if (Direction.DOWN.equals(direccion)) {
                            x = i + 1;
                            y = j;
                        } else if (Direction.LEFT.equals(direccion)) {
                            x = i;
                            y = j - 1;
                        } else if (Direction.RIGHT.equals(direccion)) {
                            x = i;
                            y = j + 1;
                        }
                        siguiente = casilleros[x][y];
                        if (Casillero.Tipo.FRUTA.equals(siguiente.getTipo())) {
                            eventBus.post(new Callback<>(moveEvent));
                            casilleros[x][y].setTipo(Casillero.Tipo.PISO);

                        }else if(Casillero.Tipo.PISO.equals(siguiente.getTipo())) {
                            eventBus.post(new Callback<>(moveEvent));
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
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
                } else if (row[j] == -1) {
                    casilleros[i][j] = new Casillero(new Coordenada(x, y), size, size, Casillero.Tipo.CRONOMETRO);
                }
                x += size;
            }
            x = 0;
            y += size;
        }

        timer = new Timer(1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tiempo--;
                if (tiempo == 0){
                    timer.stop();
                    eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
                }
            }
        });
    }
}
