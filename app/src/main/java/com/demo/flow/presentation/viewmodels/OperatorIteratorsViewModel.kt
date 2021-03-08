package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.models.ApiUser
import com.demo.flow.network.repository.UsersRepository
import com.demo.flow.presentation.view.uiActions.OperatorIteratorsAction
import com.demo.flow.presentation.view.uiState.OperatorIteratorUiState
import com.demo.flow.presentation.view.uiState.SingleNetworkCallUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorIteratorsViewModel(
    private val repository: UsersRepository
) : ViewModel() {

    companion object {
        const val ACTION_ITERATOR = "ACTION_ITERATOR"
        const val ACTION_FOR = "ACTION_FOR"
        const val ACTION_FOR_EACH = "ACTION_FOR_EACH"
        const val ACTION_FOR_EACH_INDEXED = "ACTION_FOR_EACH_INDEXED"
    }

    private val _loginUiState =
        MutableStateFlow<OperatorIteratorUiState>(OperatorIteratorUiState.Empty)
    val operatorIteratorUiState: StateFlow<OperatorIteratorUiState> = _loginUiState

    val operatorIteratorsAction = Channel<OperatorIteratorsAction>(Channel.UNLIMITED)

    init {
        handleActions()
    }

    private fun handleActions() {
        viewModelScope.launch {
            operatorIteratorsAction.consumeAsFlow().collect {
                print("")

                /*when (it) {
                    is OperatorIteratorsAction.OperatorActionFor -> fetchUsers(ACTION_FOR)
                    is OperatorIteratorsAction.OperatorActionForEach -> fetchUsers(ACTION_FOR_EACH)
                    is OperatorIteratorsAction.OperatorActionForEachIndexed -> fetchUsers(ACTION_FOR_EACH_INDEXED)
                    is OperatorIteratorsAction.OperatorActionIterator -> fetchUsers(ACTION_ITERATOR)
                }*/
            }
        }
    }

    private fun fetchUsers(action: String) = viewModelScope.launch {
        _loginUiState.value = OperatorIteratorUiState.Loading
        repository.getPlaylists().flowOn(Dispatchers.Default)
            .catch {
                _loginUiState.value = OperatorIteratorUiState.Error(it.message!!)
            }
            .collect { result ->
                when(action) {
                    ACTION_ITERATOR  -> {
                        result.iterator().let { iterator ->
                            while (iterator.hasNext()){
                                val item = iterator.hasNext()
                                println(item)
                            }
                        }
                        _loginUiState.value = OperatorIteratorUiState.Success
                    }
                    ACTION_FOR -> {
                        result.let { itemsLst : List<ApiUser> ->
                            for (item in itemsLst) {
                                println(item)
                            }
                        }
                        _loginUiState.value = OperatorIteratorUiState.Success
                    }
                    ACTION_FOR_EACH -> {
                        result.forEach { item ->
                            println(item)
                        }
                        _loginUiState.value = OperatorIteratorUiState.Success
                    }
                    ACTION_FOR_EACH_INDEXED -> {
                        result.forEachIndexed() { index, book ->
                            println("${index + 1} - $book")
                        }
                        _loginUiState.value = OperatorIteratorUiState.Success
                    }
                }
            }
    }
}