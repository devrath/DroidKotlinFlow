package com.demo.flow.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.models.ApiUser
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.utils.Constants
import com.demo.flow.view.actions.ParallelNetworkCallUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ParallelNetworkCallViewModel(
    private val repository : UsersRepository
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<ParallelNetworkCallUiState>(ParallelNetworkCallUiState.Empty)
    val parallelNetworkCallUiState: StateFlow<ParallelNetworkCallUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = ParallelNetworkCallUiState.Loading

        repository.getPlaylists()
            .zip(repository.getMorePlaylists()) { usersFromApi, moreUsersFromApi ->
                val allUsersFromApi = mutableListOf<ApiUser>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)
                _loginUiState.value = ParallelNetworkCallUiState.Success(allUsersFromApi)
                return@zip allUsersFromApi
            }.flowOn(Dispatchers.Default)
                .catch { _loginUiState.value = ParallelNetworkCallUiState.Error(it.message!!) }
                .collect {
                    val mData = it as List<ApiUser>
                    _loginUiState.value = ParallelNetworkCallUiState.Success(mData)
                }
    }

}