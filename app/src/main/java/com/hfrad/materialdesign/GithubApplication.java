package com.hfrad.materialdesign;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hfrad.materialdesign.di.AppComponent;
import com.hfrad.materialdesign.di.DaggerAppComponent;
import com.hfrad.materialdesign.di.module.AppModule;
import com.hfrad.materialdesign.di.repository.RepositorySubcomponent;
import com.hfrad.materialdesign.di.user.UsersSubcomponent;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;

public class GithubApplication extends Application {

    public static final boolean DEBUG = true;

    public static GithubApplication INSTANCE;

    private ApiHolder apiHolder;

    private AppComponent appComponent;

    @Nullable
    private UsersSubcomponent userSubcomponent;

    @Nullable
    private RepositorySubcomponent repositorySubcomponent;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;

        apiHolder = new ApiHolder();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static GithubApplication getApplication() {
        return INSTANCE;
    }

    public IDataSource getApi() {
        return apiHolder.getDataSource();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @NonNull
    public UsersSubcomponent initUserSubcomponent() {
        AppComponent appComp = this.appComponent;

        if (appComp == null) {
            throw new IllegalStateException("appComponent must be initialized!!!");
        }

        if (userSubcomponent == null) {
            UsersSubcomponent userSubcomponent = appComp.userSubComponent();

            this.userSubcomponent = userSubcomponent;
        }

        return userSubcomponent;
    }

    public void releaseUserSubcomponent() {
        userSubcomponent = null;
    }

    @Nullable
    public RepositorySubcomponent initRepositoriesSubcomponent() {
        RepositorySubcomponent repositorySubcomponent = (userSubcomponent != null) ? userSubcomponent.repositorySubComponent() : null;

        this.repositorySubcomponent = repositorySubcomponent;

        return repositorySubcomponent;
    }

    public void releaseRepositorySubcomponent() {
        repositorySubcomponent = null;
    }
}
