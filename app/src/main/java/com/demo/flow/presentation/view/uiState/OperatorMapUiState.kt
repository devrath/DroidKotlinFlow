package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorMapUiState {
    data class Success(val usersList: List<ApiUser>) : OperatorMapUiState()
    data class Error(val message: String) : OperatorMapUiState()
    object Loading : OperatorMapUiState()
    object Empty : OperatorMapUiState()
}

