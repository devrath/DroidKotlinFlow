package com.demo.flow.network.services

import com.demo.flow.models.ApiUser
import com.demo.flow.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class PlayListService (
    private val api : PlaylistAPI
) {

    suspend fun fetchPlayList() : Flow<Result<List<ApiUser>>> {

        return flow {
            emit(Result.success(api.getUsers()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}