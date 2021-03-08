package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiState.OperatorMapUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorMapViewModel (
    private val repository : UsersRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow<OperatorMapUiState>(OperatorMapUiState.Empty)

    val operatorMapUiState: StateFlow<OperatorMapUiState> = _loginUiState

    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = OperatorMapUiState.Loading
        repository.getPlaylists()
            .map { userList ->
                // We have a list of users
                userList.filter {
                    // We have a single user
                        item -> item.name.startsWith("a",ignoreCase = true)
                }
            }
            .flowOn(Dispatchers.Default)
            .catch {
                _loginUiState.value = OperatorMapUiState.Error(it.message!!)
            }
            .collect {
                _loginUiState.value = OperatorMapUiState.Success(it)
            }
    }
}