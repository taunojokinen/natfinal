package com.example.eprice.model

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat

import java.util.Calendar
import java.util.Locale

class TodoViewModel : ViewModel(){

    val modos= mutableListOf<String>()





    init {
        modos.add("Test 1")
        modos.add("Test 2")
        modos.add("Test 3")
    }

    companion object {
        fun getCurrentDateAndHour(): Any {
            val calendar = Calendar.getInstance()
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val hourFormatter = SimpleDateFormat("HH", Locale.getDefault())
            val date = dateFormatter.format(calendar.time)
            val hour = hourFormatter.format(calendar.time)
            return "date=$date&hour=$hour"
        }
    }
}
