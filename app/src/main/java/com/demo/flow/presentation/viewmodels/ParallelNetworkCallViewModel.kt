package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.models.ApiUser
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiState.ParallelNetworkCallUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ParallelNetworkCallViewModel(
    private val repository : UsersRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<ParallelNetworkCallUiState>(
        ParallelNetworkCallUiState.Empty)
    val parallelNetworkCallUiState: StateFlow<ParallelNetworkCallUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = ParallelNetworkCallUiState.Loading

        repository.getPlaylists()
            .zip(repository.getMorePlaylists()) { usersFromApi, moreUsersFromApi ->
                return@zip usersFromApi + moreUsersFromApi
            }.flowOn(Dispatchers.Default)
                .catch { _loginUiState.value = ParallelNetworkCallUiState.Error(it.message!!) }
                .collect {
                    val mData = it as List<ApiUser>
                    _loginUiState.value = ParallelNetworkCallUiState.Success(mData)
                }
    }

}