package com.hfrad.materialdesign.ui.api

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.hfrad.materialdesign.R
import com.hfrad.materialdesign.ui.marsweather.MarsWeatherData
import com.hfrad.materialdesign.ui.marsweather.MarsWeatherViewModel
import com.hfrad.materialdesign.ui.picture.PictureOfTheDayData
import com.hfrad.materialdesign.ui.picture.PictureOfTheDayViewModel
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_mars.*

class MarsFragment : Fragment() {

    private val viewModel: MarsWeatherViewModel by lazy {
        ViewModelProviders.of(this).get(MarsWeatherViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData()
            .observe(this@MarsFragment, Observer<MarsWeatherData> { renderData(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mars, container, false)
    }


    private fun renderData(data: MarsWeatherData) {
        when (data) {
            is MarsWeatherData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = "https://avatars.mds.yandex.net/get-zen_doc/3721416/pub_5f42a8e74883df77dace8e15_5f42a91caee5d15985f8e31d/scale_1200"
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    mars_weather_picture.load(url) {
                        lifecycle(this@MarsFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)

                       mars_weather_date.text = "Дата: "+serverResponseData.Soul767.last_UTC
                        mars_weather_pressure_avg.text = "Давление: "+serverResponseData.Soul767.pRE.av.toString()+" Pa"
                        mars_season.text = "Время года: "+serverResponseData.Soul767.season

                    }
                }
            }
            is MarsWeatherData.Loading -> {
                //showLoading()
            }
            is MarsWeatherData.Error -> {
                //showError(data.error.message)
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}
