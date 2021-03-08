package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorPartitionUiState {
    data class Success(val usersList: List<ApiUser>) : OperatorPartitionUiState()
    data class Error(val message: String) : OperatorPartitionUiState()
    object Loading : OperatorPartitionUiState()
    object Empty : OperatorPartitionUiState()
}

