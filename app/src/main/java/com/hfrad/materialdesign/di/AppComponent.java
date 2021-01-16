package com.hfrad.materialdesign.di;

import javax.inject.Singleton;

import dagger.Component;
import com.hfrad.materialdesign.MainActivity;
import com.hfrad.materialdesign.di.module.ApiModule;
import com.hfrad.materialdesign.di.module.AppModule;
import com.hfrad.materialdesign.di.module.CacheModule;
import com.hfrad.materialdesign.di.module.CiceroneModule;
import com.hfrad.materialdesign.di.module.ImageModule;
import com.hfrad.materialdesign.di.user.UsersSubcomponent;
import com.hfrad.materialdesign.mvp.presenter.MainPresenter;

@Singleton
@Component (
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                ImageModule.class
        }
)

public interface AppComponent {
    UsersSubcomponent userSubComponent();

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
}
