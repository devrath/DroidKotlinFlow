package com.demo.flow.network.api

import com.demo.flow.models.PlaylistItem
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchPlayList() : List<PlaylistItem>

}