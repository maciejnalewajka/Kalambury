package com.example.android.kalambury.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.kalambury.R
import com.example.android.kalambury.databinding.GameFragmentBinding

class GameFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : GameFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
        return binding.root
    }
}