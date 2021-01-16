package com.hfrad.materialdesign;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.hfrad.materialdesign.mvp.model.api.IDataSource;

public class ApiHolder {
    private IDataSource dataSource;

    ApiHolder() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dataSource = retrofit.create(IDataSource.class);
    }


    public IDataSource getDataSource() {
        return dataSource;
    }}