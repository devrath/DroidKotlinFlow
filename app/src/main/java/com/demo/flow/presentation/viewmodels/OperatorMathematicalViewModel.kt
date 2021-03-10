package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.presentation.view.uiActions.OperatorMathematicalAction
import com.demo.flow.presentation.view.uiState.OperatorMathematicalUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorMathematicalViewModel() : ViewModel() {

    companion object {
        const val ACTION_ITERATOR = "ACTION_ITERATOR"
        const val ACTION_FOR = "ACTION_FOR"
        const val ACTION_FOR_EACH = "ACTION_FOR_EACH"
        const val ACTION_FOR_EACH_INDEXED = "ACTION_FOR_EACH_INDEXED"
    }

    private val _loginUiState =
        MutableStateFlow<OperatorMathematicalUiState>(OperatorMathematicalUiState.Empty)
    val operatorMathematicalUiState: StateFlow<OperatorMathematicalUiState> = _loginUiState

    val operatorMathAction = Channel<OperatorMathematicalAction>(Channel.UNLIMITED)

    init {
        handleActions()
    }

    private fun handleActions() {
        viewModelScope.launch {
            operatorMathAction.consumeAsFlow().collect {
                when (it) {
                    is OperatorMathematicalAction.OperatorAverage -> TODO()
                    is OperatorMathematicalAction.OperatorMathSum -> TODO()
                    is OperatorMathematicalAction.OperatorMathSumBy -> TODO()
                    is OperatorMathematicalAction.OperatorMaxBy -> TODO()
                    is OperatorMathematicalAction.OperatorMinBy -> TODO()
                }
            }
        }
    }

    private fun fetchUsers(action: String) = viewModelScope.launch {
        //_loginUiState.value = OperatorIteratorUiState.Loading

    }
}