package com.hfrad.materialdesign.mvp.model.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import com.hfrad.materialdesign.mvp.model.entity.GithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.Picture;

public interface IGithubRepositoriesRepo {
    Single<List<GithubRepository>> getRepositories(Picture user);
}
