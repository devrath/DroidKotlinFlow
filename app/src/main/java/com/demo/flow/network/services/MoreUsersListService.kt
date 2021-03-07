package com.demo.flow.network.services

import com.demo.flow.models.ApiUser
import com.demo.flow.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MoreUsersListService (
    private val api : PlaylistAPI
) {

    suspend fun fetchMoreUserList() : Flow<List<ApiUser>> {
        return flow {
            emit(api.getMoreUsers())
        }.catch { exception ->
            throw exception
        }
    }

}