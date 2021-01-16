package com.hfrad.materialdesign.mvp.model.repo.retrofit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;
import com.hfrad.materialdesign.mvp.model.cache.IGithubUsersCache;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.model.network.INetworkStatus;
import com.hfrad.materialdesign.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    final IGithubUsersCache cache;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<Picture> getCharacters() {
        return networkStatus.isOnlineSingle().flatMap((isOline)-> {
            // Мапируем сетевой статус к данным
            if (isOline) {
                return api.getPicture().flatMap((users) -> {
                    return cache.putUsers(users).toSingleDefault(users);
                });
            } else {
                return cache.getUsers();
            }
        }).subscribeOn(Schedulers.io());
    }
}
