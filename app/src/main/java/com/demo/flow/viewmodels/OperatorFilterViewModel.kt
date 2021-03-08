package com.demo.flow.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.models.ApiUser
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.view.actions.OperatorFilterUiState
import com.demo.flow.view.actions.ParallelNetworkCallUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorFilterViewModel(
    private val repository : UsersRepository
) : ViewModel() {
    private val _loginUiState = MutableStateFlow<OperatorFilterUiState>(OperatorFilterUiState.Empty)
    val operatorFilterUiState: StateFlow<OperatorFilterUiState> = _loginUiState


    fun fetchUsers() = viewModelScope.launch {
        _loginUiState.value = OperatorFilterUiState.Loading

        repository.getPlaylists()
            .filter { mList ->
                mList.filter {  item -> !item.name.startsWith("a",true) }
                true
            }
            .flowOn(Dispatchers.Default)
            .catch { _loginUiState.value = OperatorFilterUiState.Error(it.message!!) }
            .collect { _loginUiState.value = OperatorFilterUiState.Success(it) }
    }

}