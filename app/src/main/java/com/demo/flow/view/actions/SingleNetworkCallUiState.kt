package com.demo.flow.view.actions

import com.demo.flow.models.ApiUser

sealed class SingleNetworkCallUiState {
    data class Success(val usersList: List<ApiUser>) : SingleNetworkCallUiState()
    data class Error(val message: String) : SingleNetworkCallUiState()
    object Loading : SingleNetworkCallUiState()
    object Empty : SingleNetworkCallUiState()
}