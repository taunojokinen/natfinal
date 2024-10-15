package com.example.eprice.model

import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel(){

    val modos= mutableListOf<String>()


    init {
        modos.add("Test 1")
        modos.add("Test 2")
        modos.add("Test 3")
    }
}