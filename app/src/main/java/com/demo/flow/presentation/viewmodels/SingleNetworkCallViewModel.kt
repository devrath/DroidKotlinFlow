package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.*
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiState.SingleNetworkCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SingleNetworkCallViewModel(
    private val repository : UsersRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<SingleNetworkCallUiState>(SingleNetworkCallUiState.Empty)
    val singleNetworkCallUiState: StateFlow<SingleNetworkCallUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = SingleNetworkCallUiState.Loading

        repository.getPlaylists().catch { e ->
            _loginUiState.value = SingleNetworkCallUiState.Error(e.toString())
        }.collect {
            _loginUiState.value = SingleNetworkCallUiState.Success(it)
        }
    }

}

