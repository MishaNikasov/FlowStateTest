package com.lampa.flowstatetest.ui.fragment

import com.lampa.flowstatetest.R
import com.lampa.flowstatetest.viewmodel.PostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lampa.flowstatetest.databinding.FragmentThirdBinding
import com.lampa.flowstatetest.ui.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment: BaseFragment<FragmentThirdBinding>() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
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