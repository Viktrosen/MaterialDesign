package com.hfrad.materialdesign.ui.marsweather

import com.hfrad.materialdesign.ui.marsweather.pojo.MarsWeather
import com.hfrad.materialdesign.ui.picture.PODServerResponseData
import com.hfrad.materialdesign.ui.picture.PictureOfTheDayData

sealed class MarsWeatherData {
    data class Success(val serverResponseData: MarsWeather) : MarsWeatherData()
    data class Error(val error: Throwable) : MarsWeatherData()
    data class Loading(val progress: Int?) : MarsWeatherData()
}