package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.LibraryRepository
import com.demo.flow.presentation.view.uiActions.OperatorMathematicalAction
import com.demo.flow.presentation.view.uiState.OperatorMathematicalUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorMathematicalViewModel(
    private val repository : LibraryRepository
) : ViewModel() {

    companion object {
        const val ACTION_AVERAGE = "ACTION_AVERAGE"
        const val ACTION_MATH_SUM = "ACTION_MATH_SUM"
        const val ACTION_MATH_SUM_BY = "ACTION_MATH_SUM_BY"
        const val ACTION_MATH_MAX_BY = "ACTION_MATH_MAX_BY"
        const val ACTION_MATH_MIN_BY = "ACTION_MATH_MIN_BY"
    }

    private val _uiState = MutableStateFlow<OperatorMathematicalUiState>(OperatorMathematicalUiState.Empty)
    val operatorMathematicalUiState: StateFlow<OperatorMathematicalUiState> = _uiState

    val operatorMathAction = Channel<OperatorMathematicalAction>(Channel.UNLIMITED)

    init {
        handleActions()
    }

    private fun handleActions() {
        viewModelScope.launch {
            operatorMathAction.consumeAsFlow().collect {
                when (it) {
                    is OperatorMathematicalAction.OperatorAverage -> getTotalPriceOfBooks(ACTION_AVERAGE)
                    is OperatorMathematicalAction.OperatorMathSum -> getTotalPriceOfBooks(ACTION_MATH_SUM)
                    is OperatorMathematicalAction.OperatorMathSumBy -> getTotalPriceOfBooks(ACTION_MATH_SUM_BY)
                    is OperatorMathematicalAction.OperatorMaxBy -> getTotalPriceOfBooks(ACTION_MATH_MAX_BY)
                    is OperatorMathematicalAction.OperatorMinBy -> getTotalPriceOfBooks(ACTION_MATH_MIN_BY)
                }
            }
        }
    }

    private fun getTotalPriceOfBooks(operation : String) = viewModelScope.launch {
        repository.getBooksLists()
            .flowOn(Dispatchers.Default)
            .catch { _uiState.value = OperatorMathematicalUiState.Error(it.message!!) }
            .map { listOfUsers ->
                // Transformation operation - returns list of authors
                listOfUsers.map { it.price }
            }
            .collect { listOfPrice ->
              when (operation) {
                  ACTION_AVERAGE -> operatorAverage(listOfPrice)
                  ACTION_MATH_SUM -> operatorMathSum(listOfPrice)
                  ACTION_MATH_SUM_BY -> operatorMathSumBy(listOfPrice)
                  ACTION_MATH_MAX_BY -> operatorMaxBy(listOfPrice)
                  ACTION_MATH_MIN_BY -> operatorMinBy(listOfPrice)
              }
            }
    }

    private fun operatorMathSum(listOfPrice: List<Int>) {
        listOfPrice.sum().also {
            _uiState.value = OperatorMathematicalUiState.Success(""+it)
        }
    }

    private fun operatorMinBy(listOfPrice: List<Int>) {
        listOfPrice.minByOrNull { it }.also {
            _uiState.value = OperatorMathematicalUiState.Success(""+it)
        }
    }

    private fun operatorMaxBy(listOfPrice: List<Int>) {
        listOfPrice.maxByOrNull { it }.also {
            _uiState.value = OperatorMathematicalUiState.Success(""+it)
        }
    }

    private fun operatorMathSumBy(listOfPrice: List<Int>) {
        listOfPrice.sumBy { it }.also {
            _uiState.value = OperatorMathematicalUiState.Success(""+it)
        }
    }

    private fun operatorAverage(listOfPrice: List<Int>) {
        listOfPrice.average().also {
            _uiState.value = OperatorMathematicalUiState.Success(""+it)
        }
    }


}