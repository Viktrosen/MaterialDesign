package com.hfrad.materialdesign.ui.marsweather.pojo
import com.google.gson.annotations.SerializedName

data class PRE (
	@SerializedName("av") val av : Double,
	@SerializedName("ct") val ct : Int,
	@SerializedName("mn") val mn : Double,
	@SerializedName("mx") val mx : Double
)