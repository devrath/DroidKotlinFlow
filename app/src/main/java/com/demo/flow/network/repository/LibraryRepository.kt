package com.demo.flow.network.repository

import com.demo.flow.network.services.LibraryService
import com.demo.flow.utils.Book
import kotlinx.coroutines.flow.Flow

class LibraryRepository (
    private val service: LibraryService,
) {
    suspend fun getBooksLists() : Flow<List<Book>> {
        return service.fetchBooksList()
    }
}