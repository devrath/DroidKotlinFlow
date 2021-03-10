package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.network.repository.LibraryRepository
import com.demo.flow.presentation.view.uiState.OperatorReduceUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorReduceViewModel (
    private val repository : LibraryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<OperatorReduceUiState>(OperatorReduceUiState.Empty)

    val operatorReduceUiState: StateFlow<OperatorReduceUiState> = _uiState

    fun getTotalPriceOfBooks() = viewModelScope.launch {
        repository.getBooksLists()
            .flowOn(Dispatchers.Default)
            .catch { _uiState.value = OperatorReduceUiState.Error(it.message!!) }
            .map { listOfUsers ->
                // Transformation operation - returns list of authors
                listOfUsers.map { it.price }
            }
            .collect { listOfPrice ->
                // Reduce operation - returns the sum of all the values in list
                /**
                 * Accumulator : This is the accumulated value
                 * Price : New value we are using to modify the accumulator
                 */
                val finalResult = listOfPrice.reduce { acc, price -> acc + price }
                _uiState.value = OperatorReduceUiState.Success(finalResult.toString())
            }

    }
}