package com.hfrad.materialdesign.mvp.model.cache;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import com.hfrad.materialdesign.mvp.model.entity.Picture;

public interface IGithubUsersCache {
    Single<Picture> getUsers();
    Completable putUsers(Picture users);
}

