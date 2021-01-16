package com.hfrad.materialdesign.mvp.presenter;

import javax.inject.Inject;

import moxy.MvpPresenter;
import com.hfrad.materialdesign.GithubApplication;
import com.hfrad.materialdesign.mvp.view.MainView;
import com.hfrad.materialdesign.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter() {
        super();

        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // TODO: Nothing to do

        router.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked() {
        router.exit();
    }

}
