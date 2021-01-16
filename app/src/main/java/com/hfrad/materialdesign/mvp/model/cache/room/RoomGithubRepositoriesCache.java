package com.hfrad.materialdesign.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.materialdesign.mvp.model.cache.IGithubRepositoriesCache;
import com.hfrad.materialdesign.mvp.model.entity.GithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.Picture;
import com.hfrad.materialdesign.mvp.model.entity.room.Database;
import com.hfrad.materialdesign.mvp.model.entity.room.RoomGithubRepository;
import com.hfrad.materialdesign.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubRepositoriesCache implements IGithubRepositoriesCache {
    private final Database db;

    public RoomGithubRepositoriesCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<List<GithubRepository>> getUserRepos(Picture user) {
        return Single.fromCallable(()-> {

            RoomGithubUser roomUser = db.userDao().findByLogin(user.getDate());

            if (roomUser == null) {
                throw new RuntimeException("No such user in cache");
            }

            List<RoomGithubRepository> roomGithubRepository = db.repositoryDao().findByUser(roomUser.getTitle());

            List<GithubRepository> githubRepositoryList = new ArrayList<>();

            for (RoomGithubRepository roomGithubrepository : roomGithubRepository) {
                GithubRepository githubRepository = new GithubRepository(roomGithubrepository.getId(),
                        roomGithubrepository.getName(),
                        roomGithubrepository.getType());

                githubRepositoryList.add(githubRepository);
            }

            return githubRepositoryList;
        });
    }

    @Override
    public Completable putUserRepos(Picture user, List<GithubRepository> repositories) {
        return Completable.fromAction(()->{
            RoomGithubUser roomUser = db.userDao().findByLogin(user.getDate());

            List<RoomGithubRepository> roomGithubRepositories = new ArrayList<>();

            for (GithubRepository repo: repositories) {
                RoomGithubRepository roomRepo = new RoomGithubRepository(repo.getId(), repo.getName(), repo.getType(), roomUser.getTitle());
                roomGithubRepositories.add(roomRepo);
            }

            db.repositoryDao().insert(roomGithubRepositories);
        }).subscribeOn(Schedulers.io());
    }
}
