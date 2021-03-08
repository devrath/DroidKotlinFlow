package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiActions.OperatorIteratorsAction
import com.demo.flow.presentation.view.uiState.OperatorIteratorUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorIteratorsViewModel (
    private val repository : UsersRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<OperatorIteratorUiState>(OperatorIteratorUiState.Empty)
    val operatorIteratorUiState: StateFlow<OperatorIteratorUiState> = _loginUiState

    val operatorIteratorsAction = Channel<OperatorIteratorsAction>(Channel.UNLIMITED)

    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = OperatorIteratorUiState.Loading
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
                _loginUiState.value = OperatorIteratorUiState.Error(it.message!!)
            }
            .collect {
                _loginUiState.value = OperatorIteratorUiState.Success(it)
            }
    }


}