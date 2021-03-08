package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorFilterUiState {
    data class Success(val usersList: List<ApiUser>) : OperatorFilterUiState()
    data class Error(val message: String) : OperatorFilterUiState()
    object Loading : OperatorFilterUiState()
    object Empty : OperatorFilterUiState()
}
