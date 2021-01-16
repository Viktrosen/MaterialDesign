package com.hfrad.materialdesign.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import com.hfrad.materialdesign.GithubApplication;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.materialdesign.mvp.presenter.list.IUserListPresenter;
import com.hfrad.materialdesign.mvp.view.UsersView;
import com.hfrad.materialdesign.mvp.view.list.UserItemView;
import com.hfrad.materialdesign.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView>  {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    @Inject
    Router router;
    @Inject
    IGithubUsersRepo usersRepo;
    @Inject
    Scheduler scheduler;

    public UsersPresenter() {
        GithubApplication.INSTANCE.initUserSubcomponent().inject(this);
    }

    private class UsersListPresenter implements IUserListPresenter {

        private List<Picture> users = new ArrayList<>();


        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }

            Picture user = users.get(view.getPos());
            router.navigateTo(new Screens.UserScreen(user));
        }

        @Override
        public void bindView(UserItemView view) {
            Picture picture = users.get(view.getPos());
            view.setTitle(picture.getTitle());
            view.loadPicture(picture.getHDUrl());
            view.setExplanation(picture.getExplanation());

        }

        @Override
        public int getCount() {
            return users.size();
        }
    }

    private UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUsersListPresenter() {
        return usersListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();

    }

    private void loadData() {
        usersRepo.getCharacters().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.users.clear();
            usersListPresenter.users.add(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error: " + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getViewState().release();
    }
}
