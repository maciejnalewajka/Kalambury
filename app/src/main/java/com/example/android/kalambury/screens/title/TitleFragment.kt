package com.example.android.kalambury.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.kalambury.R
import com.example.android.kalambury.databinding.TitleFragmentBinding

class TitleFragment : Fragment(){

    private lateinit var viewModel: TitleViewModel
    private var sound: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        sound = viewModel.soundpol.load(this.context, R.raw.steal, 1)
        val binding : TitleFragmentBinding = DataBindingUtil.inflate(inflater,
            R.layout.title_fragment, container, false)
        binding.titleViewModel = viewModel
        binding.lifecycleOwner = this
        binding.buttonStart.setOnClickListener{
            viewModel.soundpol.play(sound, 1F,1F,0,0,1F)
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        return binding.root
    }
}