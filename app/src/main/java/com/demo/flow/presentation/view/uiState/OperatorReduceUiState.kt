package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorReduceUiState {
    data class Success(val result: String) : OperatorReduceUiState()
    data class Error(val message: String) : OperatorReduceUiState()
    object Loading : OperatorReduceUiState()
    object Empty : OperatorReduceUiState()
}