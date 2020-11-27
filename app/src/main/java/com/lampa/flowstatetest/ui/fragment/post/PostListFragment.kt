package com.lampa.flowstatetest.ui.fragment.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.lampa.flowstatetest.R
import com.lampa.flowstatetest.databinding.FragmentPostListBinding
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.adapter.PostAdapter
import com.lampa.flowstatetest.ui.fragment.base.BaseFragment
import com.lampa.flowstatetest.ui.utils.UiState
import com.lampa.flowstatetest.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostListFragment: BaseFragment<FragmentPostListBinding>() {

    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_list, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.postInteraction = postInteraction
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
        if (viewModel.postsList.value == null) {
            viewModel.getPostList()
        }
    }

    private fun setupViewModelCallbacks() {
        lifecycleScope.launchWhenStarted {
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

        lifecycleScope.launchWhenStarted {
            viewModel.postsList.collect { list ->
                binding.postList = list
            }
        }
    }

    private val postInteraction = object : PostAdapter.Interaction {
        override fun onItemSelected(position: Int, item: PostResponseItem) {
            findNavController().navigate(R.id.postFragment, PostFragment.getBundle(item.id))
        }
    }

    private fun showProgress() {
        binding.progress.isVisible = true
    }

    private fun hideProgress() {
        binding.progress.isVisible = false
    }
}