package com.example.eprice.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class Eprice(
    var price: Float
)

const val BASE_URL = "https://api.porssisahko.net/v1/price.json?date=2024-10-15&hour=13"

interface EpriceApi{
    @GET("todos")
    suspend fun getEprice():List<Eprice>

    companion object : EpriceApi {
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
