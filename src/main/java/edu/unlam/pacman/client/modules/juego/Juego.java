package edu.unlam.pacman.client.modules.juego;

import java.awt.HeadlessException;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import com.google.common.eventbus.Subscribe;

import edu.unlam.pacman.client.modules.BaseFrame;
import edu.unlam.pacman.client.modules.juego.personaje.fantasma.FantasmaPresenter;
import edu.unlam.pacman.client.modules.juego.personaje.pacman.PacmanPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.TableroPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.cronometro.CronometroPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.glass.GlassPresenter;
import edu.unlam.pacman.client.modules.juego.tablero.puntaje.PuntajePresenter;
import edu.unlam.pacman.client.modules.login.login.Jugador;
import edu.unlam.pacman.client.sprites.fantasma.Color;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;

/**
 * @author Cristian Miranda
 * @since 10/2/15 - 17:35
 */
public class Juego extends BaseFrame {
    public Juego() throws HeadlessException {
        // Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize content
        initContent();

        // Display the window.
        setSize(JuegoConstants.MAX_WIDTH, JuegoConstants.MAX_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    protected void initContent() {
        addComponent(new TableroPresenter());
        addComponent(new PuntajePresenter());
    }

    @Override
    protected ScreenEvent.ScreenType getScreenType() {
        return ScreenEvent.ScreenType.JUEGO;
    }

    @Subscribe
    @Override
    public void handleScreenEvent(ScreenEvent screenEvent) {
        if (getScreenType().equals(screenEvent.getScreenType())) {
            initPlayers(screenEvent.getParams());
            addComponent(new CronometroPresenter());
            addComponent(new GlassPresenter());
            setVisible(true);
        } else {
            setVisible(false);
        }
    }

    private void initPlayers(Map<String, Object> params) {
        List<Jugador> jugadores = (List<Jugador>) params.get("jugadores");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugador = jugadores.get(i);
            if (i == 0) {
                addComponent(new PacmanPresenter(jugador));
            } else {
                Coordenada origen = new Coordenada(100, 100);
                Color color = Color.CELESTE;
                switch (i) {
                    case 2:
                        color = Color.ROJO;
                        origen = new Coordenada(150, 100);
                        break;
                    case 3:
                        color = Color.VERDE;
                        origen = new Coordenada(200, 100);
                        break;
                    case 4:
                        color = Color.VIOLETA;
                        origen = new Coordenada(250, 100);
                        break;
                }
                addComponent(new FantasmaPresenter(jugador, origen, color));
            }
        }
    }
}
