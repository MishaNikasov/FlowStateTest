package com.lampa.flowstatetest.ui.fragment.audio

import com.lampa.flowstatetest.R
import com.lampa.flowstatetest.viewmodel.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lampa.flowstatetest.databinding.FragmentAudioBinding
import com.lampa.flowstatetest.ui.fragment.BaseFragment

class AudioFragment: BaseFragment() {

    private lateinit var binding: FragmentAudioBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_audio, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        loadData()
        setupViewModelCallbacks()
    }

    private fun setupUi() {

    }

    private fun loadData() {

    }

    private fun setupViewModelCallbacks() {

    }
}