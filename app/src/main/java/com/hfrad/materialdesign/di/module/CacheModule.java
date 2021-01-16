package com.hfrad.materialdesign.di.module;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.hfrad.materialdesign.GithubApplication;
import com.hfrad.materialdesign.mvp.model.entity.room.Database;

@Module
public class CacheModule {
    @Singleton
    @Provides
    Database database() {
        return Room.databaseBuilder(GithubApplication.INSTANCE, Database.class, Database.DB_NAME)
                .build();
    }
}
