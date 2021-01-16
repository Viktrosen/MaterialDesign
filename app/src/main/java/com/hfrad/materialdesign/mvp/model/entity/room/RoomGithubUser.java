package com.hfrad.materialdesign.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomGithubUser {
    @PrimaryKey @NonNull
    public String title;

    public String date;
    public String hdurl;
    public String exsplanation;
    public String url;

    public RoomGithubUser() {
    }

    public RoomGithubUser(String title, String date, String hdurl, String exsplanation, String url) {
        this.title = title;
        this.date = date;
        this.hdurl = hdurl;
        this.exsplanation = exsplanation;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getUrl() {
        return url;
    }

    public String getExsplanation() {
        return exsplanation;
    }
}
