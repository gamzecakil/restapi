package com.gamzeuysal.restapi.model

import com.google.gson.annotations.SerializedName

data class PhoneModel(
     @SerializedName("id")
     val id:Int,
     @SerializedName("marka")
     val brand:String,
     @SerializedName("model")
     val model:String,
     @SerializedName("fiyat")
     val price:Double,
     @SerializedName("renk")
     val color:ArrayList<String>,
     @SerializedName("kamera")
     val camera :Camera,
     @SerializedName("stok")
     val stocks:Boolean,
     @SerializedName("firma")
     val firmName:String,
     @SerializedName("resim")
     val imageUrl : String
) {
}