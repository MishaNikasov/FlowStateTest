package com.lampa.flowstatetest.network

import com.lampa.flowstatetest.network.model.PostResponseItem
import retrofit2.Response
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi
) {
    suspend fun getPostList(): Response<List<PostResponseItem?>?> {
        return postApi.getPostList()
    }
    suspend fun getPost(id: Int?): Response<PostResponseItem?> {
        return postApi.getPost(id)
    }
}