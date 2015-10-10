package edu.unlam.pacman.ui.modules.estadisticas.estadisticas;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class EstadisticasPresenter extends Presenter<EstadisticasView> implements EstadisticasView.MyView {
    public EstadisticasPresenter() {
        super(new EstadisticasView());
    }
}
