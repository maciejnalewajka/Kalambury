package com.example.android.kalambury.screens.score

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    val soundpol: SoundPool

    private val _pkt =  MutableLiveData<Int>()
    val pkt: LiveData<Int>
        get() = _pkt

    private val _eventLanMorTajm =  MutableLiveData<Boolean>()
    val eventLanMorTajm: LiveData<Boolean>
        get() = _eventLanMorTajm

    init {
        _pkt.value = finalScore
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            soundpol = SoundPool.Builder()
                .setMaxStreams(6)
                .setAudioAttributes(audioAttributes)
                .build()
        }
        else{
            soundpol = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        }
    }

    fun lanMorTajm(){
        _eventLanMorTajm.value = true
    }

    fun onPlayAgainComplete(){
        _eventLanMorTajm.value = false
    }
}
