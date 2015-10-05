package edu.unlam.pacman.ui.modules.tablero;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:25
 */
public class TableroPresenter extends Presenter<TableroView> implements TableroView.MyView {
    public TableroPresenter() {
        super(new TableroView());
    }
}
