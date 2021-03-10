package com.demo.flow.network.services

import com.demo.flow.utils.Book
import com.demo.flow.utils.Library
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LibraryService () {
    suspend fun fetchBooksList() : Flow<List<Book>> {
        return flow {
            emit(Library.books)
        }.catch { exception ->
            throw exception
        }
    }

}