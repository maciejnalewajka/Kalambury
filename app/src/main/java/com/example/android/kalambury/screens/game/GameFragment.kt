package com.example.android.kalambury.screens.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.kalambury.R
import com.example.android.kalambury.databinding.GameFragmentBinding

class GameFragment : Fragment(){

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: GameFragmentBinding
    private var soundOlNol: Int = 0
    private var soundOlJe: Int = 0
    private var soundKoniec: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.game_fragment, container, false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        soundOlNol = viewModel.soundpol.load(this.context, R.raw.wrong, 1)
        soundOlJe = viewModel.soundpol.load(this.context, R.raw.accept, 1)
        soundKoniec = viewModel.soundpol.load(this.context, R.raw.success, 1)

        viewModel.eventGameComplete.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished) {
                val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.pkt.value ?: 0)
                findNavController(this).navigate(action)
                viewModel.koniec(soundKoniec)
            }
        })
        binding.buttonJe.setOnClickListener {
            viewModel.olJe(soundOlJe)
        }
        binding.buttonNol.setOnClickListener {
            viewModel.olNol(soundOlNol)
        }

        return binding.root
    }
}