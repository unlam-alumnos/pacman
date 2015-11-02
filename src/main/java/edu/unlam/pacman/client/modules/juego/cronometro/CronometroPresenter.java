package edu.unlam.pacman.client.modules.juego.cronometro;

import edu.unlam.pacman.client.mvp.Presenter;
import edu.unlam.pacman.shared.SharedConstants;
import edu.unlam.pacman.shared.comunication.bus.events.ScreenEvent;
import edu.unlam.pacman.shared.model.Coordenada;
import edu.unlam.pacman.shared.util.PropertiesUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by gmartin on 27/10/2015.
 */
public class CronometroPresenter extends Presenter<CronometroView> implements CronometroView.MyView {

    private final int duracion = Integer.parseInt(PropertiesUtils.pref().get(SharedConstants.GAME_LENGTH, null));
    private Cronometro cronometro = new Cronometro(duracion);
    private Timer timekeeper;
    private Coordenada coordenada = new Coordenada(200,200);

    public CronometroPresenter() {
        super(new CronometroView());
        initTimer();
        timekeeper.start();
    }

    private void initTimer(){
        timekeeper = new Timer(1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                cronometro.less();
                if (cronometro.getDuracion() == 0){
                    timekeeper.stop();
                    eventBus.post(new ScreenEvent(ScreenEvent.ScreenType.RESULTADO));
                }
            }
        });
    }

    @Override
    public void paint() {
        getView().paint(coordenada, cronometro.getValueString());
    }
}
