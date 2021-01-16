package com.hfrad.materialdesign.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Picture implements Parcelable {
    @Expose private String date;
    @Expose private String explanation;
    @Expose private String hdurl;
    @Expose private String title;
    @Expose private String url;


    public Picture(String date, String explanation, String hdurl, String title, String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    protected Picture(Parcel in) {
        date = in.readString();
        explanation = in.readString();
        hdurl = in.readString();
        title = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(explanation);
        dest.writeString(hdurl);
        dest.writeString(title);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHDUrl() {
        return hdurl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
