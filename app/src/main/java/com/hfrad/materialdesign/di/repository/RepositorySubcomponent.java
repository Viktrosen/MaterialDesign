package com.hfrad.materialdesign.di.repository;

import dagger.Subcomponent;
import com.hfrad.materialdesign.di.repository.module.RepositoryModule;
import com.hfrad.materialdesign.mvp.presenter.RepositoryPresenter;
import com.hfrad.materialdesign.mvp.presenter.UserPresenter;

@RepositoryScope
@Subcomponent (
        modules = {
                RepositoryModule.class
        }
)
public interface RepositorySubcomponent {
    void inject(UserPresenter userPresenter);
    void inject(RepositoryPresenter repoPresenter);
}
