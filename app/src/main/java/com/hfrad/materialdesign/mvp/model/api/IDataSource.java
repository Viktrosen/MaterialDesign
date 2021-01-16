package com.hfrad.materialdesign.mvp.model.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;
import com.hfrad.materialdesign.mvp.model.entity.GithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.Picture;

public interface IDataSource {

    @GET("/planetary/apod?api_key=udn4LxH05du3Zk9Hrb5PBG8GriVD5fQ9miFLmpBM")
    Single<Picture> getPicture();

    @GET
    Single<List<GithubRepository>> getRepositories(@Url String url);

}

