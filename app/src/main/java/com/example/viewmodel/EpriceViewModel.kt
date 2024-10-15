package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eprice.model.EpriceApi
import kotlinx.coroutines.launch

class EpriceViewModel: ViewModel() {
    var todos= mutableListOf<String>()
        private set

    init {
        getTodosList()
    }

    private fun getTodosList() {
        viewModelScope.launch {
            var epriceApi: EpriceApi?= null
            try {
                epriceApi = EpriceApi!!.getInstance()
                todos.clear()
                todos.addAll(epriceApi.getEprice())
            } catch (e:Exception){
                Log.d("EPRICEVIEWMODEL", e.message.toString())
            }
        }
    }
}