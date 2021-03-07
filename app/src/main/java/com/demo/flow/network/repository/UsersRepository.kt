package com.demo.flow.network.repository

import com.demo.flow.models.ApiUser
import com.demo.flow.network.services.MoreUsersListService
import com.demo.flow.network.services.UserListService
import kotlinx.coroutines.flow.Flow

class UsersRepository (
    private val userListService: UserListService,
    private val moreUsersListService: MoreUsersListService
) {
    suspend fun getPlaylists() : Flow<List<ApiUser>>{
        return userListService.fetchUserList()
    }
    suspend fun getMorePlaylists() : Flow<List<ApiUser>>{
        return moreUsersListService.fetchMoreUserList()
    }
}