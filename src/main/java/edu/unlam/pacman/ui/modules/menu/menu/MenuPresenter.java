package edu.unlam.pacman.ui.modules.menu.menu;

import edu.unlam.pacman.ui.mvp.Presenter;

/**
 * @author Cristian Miranda
 * @since 10/5/15 - 15:31
 */
public class MenuPresenter extends Presenter<MenuView> implements MenuView.MyView {
    public MenuPresenter() {
        super(new MenuView());
    }
}
