package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eprice.model.Eprice
import com.example.eprice.model.EpriceApi
import kotlinx.coroutines.launch

class EpriceViewModel: ViewModel() {
    private val _epriceList = MutableLiveData<Eprice>()
    val eprice: LiveData<Eprice> get() = _epriceList


    init {
        getEpriceList()
    }

    private fun getEpriceList() {
        viewModelScope.launch {
            var epriceApi: EpriceApi?= null
            Log.d("EPRICEVIEWMODEL", "trying!")

            try {
                Log.d("EPRICEVIEWMODEL", "Tauno")
                epriceApi = EpriceApi.getInstance()
                val epriceData = epriceApi.getEprice()
                _epriceList.postValue(epriceData)
                Log.d("EPRICEVIEWMODEL", "Tauno2")

            } catch (e:Exception){
                Log.d("EPRICEVIEWMODEL - Virhe", e.message.toString())
            }
        }
    }
}