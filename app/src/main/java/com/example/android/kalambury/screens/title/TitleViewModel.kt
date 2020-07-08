package com.example.android.kalambury.screens.title

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.lifecycle.ViewModel

class TitleViewModel : ViewModel() {

    val soundpol: SoundPool

    init{
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
}