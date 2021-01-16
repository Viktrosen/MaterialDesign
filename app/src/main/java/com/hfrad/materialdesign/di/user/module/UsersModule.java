package com.hfrad.materialdesign.di.user.module;

import dagger.Module;
import dagger.Provides;
import com.hfrad.materialdesign.di.user.UsersScope;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;
import com.hfrad.materialdesign.mvp.model.cache.IGithubUsersCache;
import com.hfrad.materialdesign.mvp.model.cache.room.RoomGithubUsersCache;
import com.hfrad.materialdesign.mvp.model.entity.room.Database;
import com.hfrad.materialdesign.mvp.model.network.INetworkStatus;
import com.hfrad.materialdesign.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.materialdesign.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

@Module
public class UsersModule {
    @Provides
    IGithubUsersCache usersCache(Database db) {
        return new RoomGithubUsersCache(db);
    }

    @UsersScope
    @Provides
    public IGithubUsersRepo usersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache) {
        return new RetrofitGithubUsersRepo(api, status, cache);
    }
}
