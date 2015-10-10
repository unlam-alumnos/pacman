package edu.unlam.pacman.ui.modules.resultado.resultado;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class ResultadoPresenter extends Presenter<ResultadoView> implements ResultadoView.MyView {
    public ResultadoPresenter() {
        super(new ResultadoView());
    }
}
