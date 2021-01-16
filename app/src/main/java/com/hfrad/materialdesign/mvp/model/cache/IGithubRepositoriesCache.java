package com.hfrad.materialdesign.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import com.hfrad.materialdesign.mvp.model.entity.GithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.Picture;

public interface IGithubRepositoriesCache {
    Single<List<GithubRepository>> getUserRepos(Picture user);
    Completable putUserRepos(Picture user, List<GithubRepository> repositories);
}
