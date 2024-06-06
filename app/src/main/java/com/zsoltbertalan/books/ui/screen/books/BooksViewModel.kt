package com.zsoltbertalan.books.ui.screen.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsoltbertalan.books.domain.model.Book
import com.zsoltbertalan.books.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksViewModel(private val libraryRepository: LibraryRepository) : ViewModel() {

	private val _state = MutableStateFlow(UiState())
	val state = _state.asStateFlow()

	init {
		fetchBooks()
	}

	fun fetchBooks() {
		viewModelScope.launch {
			_state.update { it.copy(loading = true) }
			libraryRepository.getLibrary().collect { result ->
				_state.update { uiState ->
					uiState.copy(loading = false, books = result)
				}
			}
		}
	}

	fun saveBookmark(book: Book) {
		viewModelScope.launch {
			libraryRepository.saveBookmarks(book = book)
		}
	}

	data class UiState(
		val loading: Boolean = false,
		val books: List<Book> = emptyList(),
		val error: Throwable? = null,
	)
}