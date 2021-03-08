package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorIteratorUiState {
    object Success : OperatorIteratorUiState()
    data class Error(val message: String) : OperatorIteratorUiState()
    object Loading : OperatorIteratorUiState()
    object Empty : OperatorIteratorUiState()
}