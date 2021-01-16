package com.hfrad.materialdesign.di.user;

import dagger.Subcomponent;
import com.hfrad.materialdesign.di.repository.RepositorySubcomponent;
import com.hfrad.materialdesign.di.user.module.UsersModule;
import com.hfrad.materialdesign.mvp.presenter.UsersPresenter;
import com.hfrad.materialdesign.ui.adapter.UserRVAdapter;

@UsersScope
@Subcomponent(
        modules = {
                UsersModule.class
        }
)
public interface UsersSubcomponent {
    RepositorySubcomponent repositorySubComponent();

    void inject(UsersPresenter usersPresenter);
    void inject(UserRVAdapter adapter);
}
