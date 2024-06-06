package com.zsoltbertalan.books.ui.screen.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zsoltbertalan.books.domain.model.Book
import com.zsoltbertalan.books.data.repository.LibraryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookmarksViewModel(private val libraryRepository: LibraryRepository) : ViewModel() {

	private val _state = MutableStateFlow(UiState())
	val state = _state.asStateFlow()

	init {
		fetchBookmarks()
	}

	fun fetchBookmarks() {
		viewModelScope.launch {
			_state.update { it.copy(loading = true) }
			libraryRepository.getBookmarks().collect{result->
				_state.update { uiState ->
					uiState.copy(loading = false, books = result)
				}
			}
		}
	}

	data class UiState(
		val loading: Boolean = false,
		val books: List<Book> = emptyList(),
		val error: Throwable? = null,
	)
}