package com.hfrad.materialdesign.ui.marsweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfrad.materialdesign.BuildConfig
import com.hfrad.materialdesign.ui.marsweather.pojo.MarsWeather
import com.hfrad.materialdesign.ui.picture.PODRetrofitImpl
import com.hfrad.materialdesign.ui.picture.PictureOfTheDayData

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsWeatherViewModel(
    private val liveDataForViewToObserve: MutableLiveData<MarsWeatherData> = MutableLiveData(),
                           private val retrofitImpl: MarsWeatherRetrofitImpl = MarsWeatherRetrofitImpl()
): ViewModel() {
    fun getData(): LiveData<MarsWeatherData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MarsWeatherData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            MarsWeatherData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getMarsWeather(apiKey).enqueue(object :
                Callback<MarsWeather> {
                override fun onResponse(
                    call: Call<MarsWeather>,
                    response: Response<MarsWeather>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                            MarsWeatherData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                MarsWeatherData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                MarsWeatherData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<MarsWeather>, t: Throwable) {
                    liveDataForViewToObserve.value = MarsWeatherData.Error(t)
                }
            })
        }
    }
}