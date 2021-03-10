package com.demo.flow.presentation.view.uiState

import com.demo.flow.models.ApiUser

sealed class OperatorLongOperationUiState {
    object  Success : OperatorLongOperationUiState()
    data class Error(val message: String) : OperatorLongOperationUiState()
    object Loading : OperatorLongOperationUiState()
    object Empty : OperatorLongOperationUiState()
}