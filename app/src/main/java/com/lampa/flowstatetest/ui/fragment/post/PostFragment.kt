package com.lampa.flowstatetest.ui.fragment.post

import com.lampa.flowstatetest.R
import com.lampa.flowstatetest.viewmodel.PostViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lampa.flowstatetest.databinding.FragmentPostBinding
import com.lampa.flowstatetest.ui.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PostFragment: BaseFragment<FragmentPostBinding>() {

    companion object {
        private const val POST_ID = "post_id"

        fun getBundle(postId: Int?): Bundle {
            val bundle = Bundle()
            postId?.let {
                bundle.putInt(POST_ID, postId)
            }
            return bundle
        }
    }

    private val viewModel: PostViewModel by viewModels()

    private var postId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postId = arguments?.getInt(POST_ID)

        setupUi()
        loadData()
        setupViewModelCallbacks()
    }

    private fun setupUi() {

    }

    private fun loadData() {
        viewModel.getPost(postId)
    }

    private fun setupViewModelCallbacks() {
        lifecycleScope.launchWhenStarted {
            viewModel.post.collect { post ->
                binding.post = post
            }
        }
    }
}