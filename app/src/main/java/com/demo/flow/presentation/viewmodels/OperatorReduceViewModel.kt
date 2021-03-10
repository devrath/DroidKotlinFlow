package com.demo.flow.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.flow.presentation.view.uiState.OperatorReduceUiState
import com.demo.flow.utils.Book
import com.demo.flow.utils.Library
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OperatorReduceViewModel () : ViewModel() {
    private val _loginUiState = MutableStateFlow<OperatorReduceUiState>(OperatorReduceUiState.Empty)

    val operatorReduceUiState: StateFlow<OperatorReduceUiState> = _loginUiState

    fun fetchUsers() = viewModelScope.launch {
        //_loginUiState.value = OperatorReduceUiState.Success(it)

        /**
         * Accumulator : This is the accumulated value
         * Price : New value we are using to modify the accumulator
         */
        Library.books
            .map { book : Book ->  book.price }
            .reduce { accumulator : Int , price : Int -> accumulator + price}
            .also { _loginUiState.value = OperatorReduceUiState.Success(it.toString()) }
    }
}