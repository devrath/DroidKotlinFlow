package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorMapUiState {
    data class Success(val user: String) : OperatorMapUiState()
    data class Error(val message: String) : OperatorMapUiState()
    object Loading : OperatorMapUiState()
    object Empty : OperatorMapUiState()
}

