package com.lampa.flowstatetest.network

import com.lampa.flowstatetest.network.NetworkUrls.GET_POSTS
import com.lampa.flowstatetest.network.model.PostResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET(GET_POSTS)
    suspend fun getPosts(): Response<List<PostResponseItem?>?>
}