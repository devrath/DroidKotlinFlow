package com.demo.flow.network.services

import com.demo.flow.models.ApiUser
import com.demo.flow.network.api.PlaylistAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserListService (
    private val api : PlaylistAPI
) {

    suspend fun fetchUserList() : Flow<List<ApiUser>> {
        return flow {
            emit(api.getUsers())
        }.catch { exception ->
            throw exception
        }
    }

}