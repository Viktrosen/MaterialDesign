package com.hfrad.materialdesign.ui.marsweather.pojo
import com.google.gson.annotations.SerializedName

data class Soul767 (

    @SerializedName("First_UTC") val first_UTC: String,
    @SerializedName("Last_UTC") val last_UTC: String,
    @SerializedName("PRE") val pRE: PRE,
    @SerializedName("Season") val season: String,
    @SerializedName("WD") val wD: WD
)