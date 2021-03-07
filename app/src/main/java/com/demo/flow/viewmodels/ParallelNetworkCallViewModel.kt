package com.demo.flow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.PlaylistRepository
import com.demo.flow.utils.Constants
import com.demo.flow.view.actions.ParallelNetworkCallUiState
import com.demo.flow.view.actions.SingleNetworkCallUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ParallelNetworkCallViewModel(
    private val repository : PlaylistRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<ParallelNetworkCallUiState>(ParallelNetworkCallUiState.Empty)
    val parallelNetworkCallUiState: StateFlow<ParallelNetworkCallUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = ParallelNetworkCallUiState.Loading

        repository.getPlaylists().catch { e ->
            _loginUiState.value = ParallelNetworkCallUiState.Error(e.toString())
        }.collect {
            if (it.isSuccess) {
                _loginUiState.value = ParallelNetworkCallUiState.Success(it.getOrNull().orEmpty())
            } else if (it.isFailure) {
                _loginUiState.value = ParallelNetworkCallUiState.Error(Constants.GENERIC_ERROR_MESSAGE)
            }
        }
    }

}