package com.hfrad.materialdesign.ui.marsweather

import com.hfrad.materialdesign.ui.marsweather.pojo.MarsWeather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MarsWeatherAPI {

    @GET("insight_weather/?feedtype=json&ver=1.0")
    fun getMarsWeather(@Query("api_key") apiKey: String): Call<MarsWeather>
}