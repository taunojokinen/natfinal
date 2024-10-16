package com.example.eprice.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eprice.model.Eprice
import com.example.eprice.model.EpriceApi
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class EpriceViewModel: ViewModel() {
    private val _epriceList = MutableLiveData<Eprice>()
    val eprice: LiveData<Eprice> get() = _epriceList

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage


    init {
        getEpriceList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getEpriceList() {
        viewModelScope.launch {
            val epriceApi: EpriceApi?
            Log.d("EPRICEVIEWMODEL", "trying!")

            try {
                Log.d("EPRICEVIEWMODEL", "Tauno")
                epriceApi = EpriceApi.getInstance()
                val currentDateAndHour = AikaViewModel.getCurrentDateAndHour()
                val (date, hour) = currentDateAndHour.split("&").map { it.split("=")[1] }
                val epriceData = epriceApi.getEprice(date, hour)
                _epriceList.postValue(epriceData)
                Log.d("EPRICEVIEWMODEL", "Tauno2")

            } catch (e:Exception){
                Log.d("EPRICEVIEWMODEL - Virhe", e.message.toString())
                _errorMessage.postValue(e.message) // Post the error message
            }
        }
    }
}