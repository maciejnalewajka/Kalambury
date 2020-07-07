package com.example.android.kalambury.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private val _pkt =  MutableLiveData<Int>()
    val pkt: LiveData<Int>
        get() = _pkt

    private val _eventLanMorTajm =  MutableLiveData<Boolean>()
    val eventLanMorTajm: LiveData<Boolean>
        get() = _eventLanMorTajm

    init {
        _pkt.value = finalScore
    }

    fun lanMorTajm(){
        _eventLanMorTajm.value = true
    }

    fun onPlayAgainComplete(){
        _eventLanMorTajm.value = false
    }
}
