package com.lampa.flowstatetest.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.adapter.PostAdapter

@BindingAdapter("postList", "postInteraction")
fun RecyclerView.setPostList(postList: List<PostResponseItem?>?, postInteraction: PostAdapter.Interaction?) {
    postList?.let {
        val layoutManager = LinearLayoutManager(this.context)
        val adapter = PostAdapter()
        postInteraction?.let {
            adapter.interaction = it
        }
        adapter.apply {
            submitList(postList)
        }
        this.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}