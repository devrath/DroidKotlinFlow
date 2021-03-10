package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiState.OperatorMapUiState
import com.demo.flow.presentation.view.uiState.OperatorReduceUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorReduceViewModel (
    private val repository: UsersRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow<OperatorReduceUiState>(OperatorReduceUiState.Empty)

    val operatorReduceUiState: StateFlow<OperatorReduceUiState> = _loginUiState

    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = OperatorReduceUiState.Loading
        repository.getPlaylists().map { listOfusers ->
            // We have a list of users ---- This is a transformation operation
            listOfusers.last() {
                // We have a single user
                    item ->
                item.name.startsWith("a", ignoreCase = true)
            }
        }.map { user ->
            user.name
        }.flowOn(Dispatchers.Default)
            .catch {
                _loginUiState.value = OperatorReduceUiState.Error(it.message!!)
            }
            .collect {
                _loginUiState.value = OperatorReduceUiState.Success(it)
            }
    }
}