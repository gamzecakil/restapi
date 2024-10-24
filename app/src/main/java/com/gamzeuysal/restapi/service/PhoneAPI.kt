package com.gamzeuysal.restapi.service

import com.gamzeuysal.restapi.model.PhoneModel
import io.reactivex.Observable
import retrofit2.http.GET

interface PhoneAPI {
    //GET,POST,DELETE,UPDATE

    //URL BASE ->http://localhost:3000
    //Burada localhost yerine kendi komut sisteminden ip config ile ögrenebilecegimiz kendi ipv4 adresini vermemiz gerekli
    //URL BASE ->"http://192.168.1.105:3000"
    @GET("telefonlar")
    fun getData():Observable<List<PhoneModel>>
}