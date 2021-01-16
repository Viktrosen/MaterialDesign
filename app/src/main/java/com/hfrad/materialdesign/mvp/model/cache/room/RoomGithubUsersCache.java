package com.hfrad.materialdesign.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.hfrad.materialdesign.mvp.model.cache.IGithubUsersCache;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.model.entity.room.Database;
import com.hfrad.materialdesign.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubUsersCache implements IGithubUsersCache {
    private final Database db;

    public RoomGithubUsersCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<Picture> getUsers() {
        return Single.fromCallable(()->{
            RoomGithubUser roomGithubUsers = db.userDao().getAll();




                Picture picture = new Picture(roomGithubUsers.getDate(),
                        roomGithubUsers.getExsplanation(),
                        roomGithubUsers.getHdurl(),
                        roomGithubUsers.getTitle(),
                        roomGithubUsers.getUrl());
            return picture;
        });
    }

    @Override
    public Completable putUsers(Picture picture) {
        return Completable.fromAction(()->{
            List<RoomGithubUser> roomGithubUsers = new ArrayList<>();


                RoomGithubUser roomUser = new RoomGithubUser(picture.getDate(),
                        picture.getExplanation(),
                        picture.getHDUrl(),
                        picture.getTitle(),
                        picture.getUrl());

                roomGithubUsers.add(roomUser);


            db.userDao().insert(roomGithubUsers);
        }).subscribeOn(Schedulers.io());
    }
}

