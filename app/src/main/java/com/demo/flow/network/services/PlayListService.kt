package com.demo.flow.network.services

import com.demo.flow.models.PlaylistItem
import com.demo.flow.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListService @Inject constructor(
    private val api : PlaylistAPI
) {

    suspend fun fetchPlayList() : Flow<Result<List<PlaylistItem>>> {

        return flow {
            emit(Result.success(api.fetchPlayList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
