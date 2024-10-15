package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eprice.model.Eprice
import com.example.eprice.model.EpriceApi
import kotlinx.coroutines.launch

class EpriceViewModel: ViewModel() {
    var todos= mutableListOf<Eprice>()
        private set

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            var epriceApi: EpriceApi?= null
            Log.d("EPRICEVIEWMODEL", "trying!")

            try {
                epriceApi = EpriceApi!!.getInstance()
                todos.clear()
                todos.addAll(listOf(epriceApi.getEprice()))
                Log.d("EPRICEVIEWMODEL", "Tauno")

            } catch (e:Exception){
                Log.d("EPRICEVIEWMODEL - Virhe", e.message.toString())
            }
        }
    }
}