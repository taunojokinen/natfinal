package com.example.eprice.model

import android.icu.text.CaseMap.Title
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.eprice.viewmodel.AikaViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class Eprice(
    var price: Float
)

const val BASE_URL = "https://api.porssisahko.net/v1/"



interface EpriceApi{

 @GET("price.json?")
    suspend fun getEprice(@Query("date") date: String, @Query("hour") hour: String): Eprice

    companion object {
        var epriceService: EpriceApi? = null

        @RequiresApi(Build.VERSION_CODES.O)
        fun getInstance(): EpriceApi {
            if (epriceService === null) {
                epriceService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(EpriceApi::class.java)
            }
            timeStamp()
            return epriceService!!

        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun timeStamp(): Any {
    val currentDateAndHour = AikaViewModel.getCurrentDateAndHour()
    Log.d("EPRICE","Current Date and Hour: $currentDateAndHour")
    return currentDateAndHour
}