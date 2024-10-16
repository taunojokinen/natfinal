package com.example.eprice.viewmodel


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AikaViewModel : ViewModel() {

        companion object {
            @RequiresApi(Build.VERSION_CODES.O)
            fun getCurrentDateAndHour(): String {
                val finnishTime = ZonedDateTime.now(ZoneId.of("Europe/Helsinki"))
                val date = finnishTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val hour = finnishTime.format(DateTimeFormatter.ofPattern("HH"))
                return "date=$date&hour=$hour"
            }

            @RequiresApi(Build.VERSION_CODES.O)
            fun getCurrentDateHourAndMinute(): Any {
                val finnishTime = ZonedDateTime.now(ZoneId.of("Europe/Helsinki"))
                val date = finnishTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                val hour = finnishTime.format(DateTimeFormatter.ofPattern("HH"))
                val minute=finnishTime.minute
                return "$date $hour $minute"
            }
        }
}