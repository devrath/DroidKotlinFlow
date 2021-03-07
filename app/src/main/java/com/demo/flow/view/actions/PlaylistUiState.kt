package com.demo.flow.view.actions

import com.demo.flow.models.ApiUser

sealed class PlaylistUiState {
    data class Success(val usersList: List<ApiUser>) : PlaylistUiState()
    data class Error(val message: String) : PlaylistUiState()
    object Loading : PlaylistUiState()
    object Empty : PlaylistUiState()
}