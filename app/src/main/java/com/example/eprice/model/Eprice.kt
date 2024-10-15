package com.example.eprice.model

import android.icu.text.CaseMap.Title
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Eprice(
    var price: Float
//    var userId: Int,
//    var id: Int,
//    var title: String,
//    var comleted: Boolean
)

const val BASE_URL = "https://api.porssisahko.net/v1/"
//const val BASE_URL = "https://jsonplaceholder.typicode.com/"


interface EpriceApi{
 //   @GET("todos")
 @GET("price.json?date=2024-10-15&hour=13")
    suspend fun getEprice():Eprice

    companion object {
        var epriceService: EpriceApi? = null

        fun getInstance(): EpriceApi {
            if (epriceService === null) {
                epriceService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(EpriceApi::class.java)
            }
            return epriceService!!

        }
    }
}
