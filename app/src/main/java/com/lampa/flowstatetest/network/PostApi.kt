package com.lampa.flowstatetest.network

import com.lampa.flowstatetest.network.NetworkUrls.GET_POST
import com.lampa.flowstatetest.network.NetworkUrls.GET_POST_LIST
import com.lampa.flowstatetest.network.model.PostResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApi {

    @GET(GET_POST_LIST)
    suspend fun getPostList(): Response<List<PostResponseItem?>?>

    @GET(GET_POST)
    suspend fun getPost(
        @Path("id") id: Int?
    ): Response<PostResponseItem?>

}