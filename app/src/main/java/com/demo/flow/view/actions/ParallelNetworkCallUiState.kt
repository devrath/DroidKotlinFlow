package com.demo.flow.view.actions

import com.demo.flow.models.ApiUser

sealed class ParallelNetworkCallUiState {
    data class Success(val usersList: List<ApiUser>) : ParallelNetworkCallUiState()
    data class Error(val message: String) : ParallelNetworkCallUiState()
    object Loading : ParallelNetworkCallUiState()
    object Empty : ParallelNetworkCallUiState()
}
