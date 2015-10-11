package edu.unlam.pacman.client.modules.resultado.resultado;

import edu.unlam.pacman.client.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class ResultadoPresenter extends Presenter<ResultadoView> implements ResultadoView.MyView {
    public ResultadoPresenter() {
        super(new ResultadoView());
    }
}
