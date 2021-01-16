package com.hfrad.materialdesign.di.repository.module;

import dagger.Module;
import dagger.Provides;
import com.hfrad.materialdesign.di.repository.RepositoryScope;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;
import com.hfrad.materialdesign.mvp.model.cache.IGithubRepositoriesCache;
import com.hfrad.materialdesign.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.hfrad.materialdesign.mvp.model.entity.room.Database;
import com.hfrad.materialdesign.mvp.model.network.INetworkStatus;
import com.hfrad.materialdesign.mvp.model.repo.IGithubRepositoriesRepo;
import com.hfrad.materialdesign.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;

@Module
public class RepositoryModule {
    @Provides
    IGithubRepositoriesCache userRepositoriesCache(Database db) {
        return new RoomGithubRepositoriesCache(db);
    }

    @RepositoryScope
    @Provides
    public IGithubRepositoriesRepo userRepositoriesRepo(IDataSource api, INetworkStatus networkStatus, IGithubRepositoriesCache cache) {
        return new RetrofitGithubRepositoriesRepo(api, networkStatus, cache);
    }
}
