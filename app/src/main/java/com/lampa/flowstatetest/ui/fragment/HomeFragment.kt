package com.lampa.flowstatetest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lampa.flowstatetest.R
import com.lampa.flowstatetest.databinding.FragmentHomeBinding
import com.lampa.flowstatetest.extensions.showToast
import com.lampa.flowstatetest.ui.utils.UiState
import com.lampa.flowstatetest.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment: BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
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
        if (viewModel.posts.value == null) {
            viewModel.getData()
        }
    }

    private fun setupViewModelCallbacks() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        showProgress()
                    }
                    is UiState.Success -> {
                        hideProgress()
                    }
                    is UiState.Error -> {
                        hideProgress()
                        showError(state.message)
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launch {
            viewModel.posts.collect { list ->
                list?.forEach {
                    it?.let {
                        Log.d("TAG", it.title)
                    }
                }
            }
        }
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }
}