package com.demo.flow.view.actions

import com.demo.flow.models.ApiUser

sealed class OperatorIteratorUiState {
    data class Success(val usersList: List<ApiUser>) : OperatorIteratorUiState()
    data class Error(val message: String) : OperatorIteratorUiState()
    object Loading : OperatorIteratorUiState()
    object Empty : OperatorIteratorUiState()
}