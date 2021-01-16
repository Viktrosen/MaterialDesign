package com.hfrad.materialdesign.mvp.model.repo.retrofit;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;
import com.hfrad.materialdesign.mvp.model.cache.IGithubRepositoriesCache;
import com.hfrad.materialdesign.mvp.model.entity.GithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.model.network.INetworkStatus;
import com.hfrad.materialdesign.mvp.model.repo.IGithubRepositoriesRepo;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    final IGithubRepositoriesCache cache;

    public RetrofitGithubRepositoriesRepo(IDataSource api, INetworkStatus status, IGithubRepositoriesCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<List<GithubRepository>> getRepositories(Picture user) {
        return networkStatus.isOnlineSingle().flatMap((isOline)-> {
            if (isOline) {
                final String url = user.getTitle();

                if (url != null) {
                    return api.getRepositories(url).flatMap((repositories) -> {
                        return cache.putUserRepos(user, repositories).toSingleDefault(repositories);
                    });
                } else {
                    return Single.fromCallable(()->{
                        final List<GithubRepository> emptyList = Collections.emptyList();
                        return emptyList;
                    });
                }
            } else {
                return cache.getUserRepos(user);
            }
        }).subscribeOn(Schedulers.io());
    }
}
