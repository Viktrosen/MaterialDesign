package com.hfrad.materialdesign.mvp.model.repo;

import io.reactivex.rxjava3.core.Single;

import com.hfrad.materialdesign.mvp.model.entity.Picture;

public interface IGithubUsersRepo {
    Single <Picture> getCharacters();
}
