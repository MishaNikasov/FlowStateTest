package com.lampa.flowstatetest.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lampa.flowstatetest.network.model.PostResponseItem
import com.lampa.flowstatetest.ui.adapter.PostAdapter

@BindingAdapter("postList")
fun RecyclerView.setPostList(postList: List<PostResponseItem?>?) {
    postList?.let {
        val layoutManager = LinearLayoutManager(this.context)
        val adapter = PostAdapter()
        adapter.apply {
            submitList(postList)
        }
        this.apply {
            this.layoutManager = layoutManager
            this.adapter = adapter
        }
    }
}