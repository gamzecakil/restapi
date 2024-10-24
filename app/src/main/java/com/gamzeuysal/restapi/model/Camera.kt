package com.gamzeuysal.restapi.model

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("on")
    val frontCameraPiksel:Int,
    @SerializedName("arka")
    val backCameraPiksel:Double ) {
}