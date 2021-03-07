package com.demo.flow.network.services

import com.demo.flow.models.ApiUser
import com.demo.flow.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MoreUsersListService (
    private val api : PlaylistAPI
) {

    suspend fun fetchMoreUserList() : Flow<Result<List<ApiUser>>> {

        return flow {
            emit(Result.success(api.getMoreUsers()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}