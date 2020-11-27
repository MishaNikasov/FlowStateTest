package com.lampa.flowstatetest.network

import com.lampa.flowstatetest.network.model.PostResponseItem
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Api
) {
    suspend fun getPosts(): Response<List<PostResponseItem?>?> {
        return api.getPosts()
    }
}