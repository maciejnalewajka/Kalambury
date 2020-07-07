package com.example.android.kalambury.screens.game

import android.app.Application
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.kalambury.R

class GameViewModel : ViewModel() {

    companion object{
        private const val ZERO = 0L
        private const val SEKUNDA = 1000L
        private const val MINUTA = 60000L
    }

    val soundpol: SoundPool
    private lateinit var listaSlow: MutableList<String>
    private val timer: CountDownTimer

    // Obecne słowo
    private val _slowo = MutableLiveData<String>()
    val slowo : LiveData<String>
        get() = _slowo

    // Obecny wynik
    private val _pkt = MutableLiveData<Int>()
    val pkt : LiveData<Int>
        get() = _pkt

    // Obecny stan gry
    private val _eventGameComplete = MutableLiveData<Boolean>()
    val eventGameComplete : LiveData<Boolean>
        get() = _eventGameComplete

    // Pozostały czas
    private val _obecnyCzas = MutableLiveData<Long>()
    private val obecnyCzas : LiveData<Long>
        get() = _obecnyCzas

    val obecnyCzasString = Transformations.map(obecnyCzas) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        resetList()
        nextWord()
        _pkt.value = 0
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

        timer = object: CountDownTimer(MINUTA, SEKUNDA){
            override fun onTick(millisUntilFinished: Long){
                _obecnyCzas.value = (millisUntilFinished / SEKUNDA)
            }
            override fun onFinish(){
                _obecnyCzas.value = ZERO
                _eventGameComplete.value = true
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private fun resetList() {
        listaSlow = mutableListOf(
            "kalambury",
            "czesanie psa",
            "branie ślubu",
            "oświadczyny",
            "murowanie",
            "wyprowadzanie psa",
            "przeciąganie liny",
            "podnoszenie ciężarów",
            "lot balonem",
            "mycie zębów",
            "branie prysznica",
            "skok w dal",
            "jogging",
            "rzut oszczepem",
            "gra w piłkę nożną",
            "gra w siatkówkę",
            "gra w koszykówkę",
            "fryzjer",
            "pielęgnowanie ogrodu",
            "drink",
            "shake",
            "czytanie",
            "granie w gry wideo",
            "prasowanie",
            "robienie kanapek",
            "pieczenie ciasta",
            "skok ze spadochronem",
            "lot samolotem",
            "pisanie na tablicy",
            "robienie prania",
            "gra w golfa",
            "gra w bilard",
            "malowanie paznokci",
            "golenie brody",
            "wykopanie skarbu",
            "pływanie kajakiem",
            "łódka",
            "malowanie",
            "sprzątanie pokoju"
        )
        listaSlow.shuffle()
    }

    private fun nextWord() {
        // Zwróć słowo i usuń z listy, zresetuj kiedy pusta
        if (listaSlow.isEmpty()) {
            resetList()
        }
        _slowo.value = listaSlow.removeAt(0)
    }

    fun olNol(soundOlNol: Int) {
        soundpol.play(soundOlNol, 1F,1F,0,0,1F)
        _pkt.value = (pkt.value)?.minus(1)
        nextWord()
    }

    fun olJe(soundOlJe: Int) {
        soundpol.play(soundOlJe, 1F,1F,0,0,1F)
        _pkt.value = (pkt.value)?.plus(1)
        nextWord()
    }

    fun koniec(soundKoniec: Int){
        soundpol.play(soundKoniec, 1F,1F,0,0,1F)
        _eventGameComplete.value = false
    }


}