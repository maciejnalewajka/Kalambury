package com.example.android.kalambury.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.kalambury.R
import com.example.android.kalambury.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment(){

    private lateinit var viewModel : ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ScoreFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.score_fragment, container, false)

        val scoreFragmentsArgs by navArgs<ScoreFragmentArgs>()
        viewModelFactory = ScoreViewModelFactory(scoreFragmentsArgs.pkt)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventLanMorTajm.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.restartAction())
                viewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}