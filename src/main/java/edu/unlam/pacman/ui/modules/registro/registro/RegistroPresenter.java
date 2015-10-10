package edu.unlam.pacman.ui.modules.registro.registro;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class RegistroPresenter extends Presenter<RegistroView> implements RegistroView.MyView {
    public RegistroPresenter() {
        super(new RegistroView());
    }
}
