package com.demo.flow.presentation.view.uiState

sealed class OperatorMathematicalUiState {
    data class Success(val result: String) : OperatorMathematicalUiState()
    data class Error(val result: String) : OperatorMathematicalUiState()
    object Empty : OperatorMathematicalUiState()
}
