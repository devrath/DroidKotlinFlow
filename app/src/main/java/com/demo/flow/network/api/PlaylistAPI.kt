package com.demo.flow.network.api

import com.demo.flow.models.ApiUser
import com.demo.flow.models.PlaylistItem
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("users")
    suspend fun getUsers(): List<ApiUser>

    @GET("more-users")
    suspend fun getMoreUsers(): List<ApiUser>

    @GET("error")
    suspend fun getUsersWithError(): List<ApiUser>

}