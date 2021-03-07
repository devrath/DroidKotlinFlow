package com.demo.flow.viewmodels

import androidx.lifecycle.*
import com.demo.flow.models.ApiUser
import com.demo.flow.network.repository.PlaylistRepository
import com.demo.flow.network.utils.Resource
import com.demo.flow.utils.Constants.GENERIC_ERROR_MESSAGE
import com.demo.flow.view.actions.PlaylistUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SingleNetworkCallViewModel(
    private val repository : PlaylistRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<PlaylistUiState>(PlaylistUiState.Empty)
    val playlistUiState: StateFlow<PlaylistUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = PlaylistUiState.Loading

        repository.getPlaylists().catch { e ->
            _loginUiState.value = PlaylistUiState.Error(e.toString())
        }.collect {
            if (it.isSuccess) {
                _loginUiState.value = PlaylistUiState.Success(it.getOrNull().orEmpty())
            } else if (it.isFailure) {
                _loginUiState.value = PlaylistUiState.Error(GENERIC_ERROR_MESSAGE)
            }
        }
    }

}

