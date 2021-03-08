package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiState.OperatorPartitionUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorPartitionViewModel (
    private val repository : UsersRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow<OperatorPartitionUiState>(OperatorPartitionUiState.Empty)

    val operatorPartitionUiState: StateFlow<OperatorPartitionUiState> = _loginUiState

    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = OperatorPartitionUiState.Loading
        repository.getPlaylists()
            .map { userList ->
                // We have a list of users ---- This is a transformation operation
                userList.filter {
                    // We have a single user
                        item -> item.name.startsWith("a",ignoreCase = true)
                }
            }
            .flowOn(Dispatchers.Default)
            .catch {
                _loginUiState.value = OperatorPartitionUiState.Error(it.message!!)
            }
            .collect {
                _loginUiState.value = OperatorPartitionUiState.Success(it)
            }
    }
}