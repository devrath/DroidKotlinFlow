package com.demo.flow.network.repository

import com.demo.flow.models.PlaylistItem
import com.demo.flow.network.services.PlayListService
import kotlinx.coroutines.flow.Flow

class PlaylistRepository (
    private val service: PlayListService
) {

    suspend fun getPlaylists() : Flow<Result<List<PlaylistItem>>>{
        return service.fetchPlayList()
    }

}