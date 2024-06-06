package com.zsoltbertalan.books.ui.screen.books

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zsoltbertalan.books.domain.model.Book
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BooksScreen(
	stateFlow: StateFlow<BooksViewModel.UiState>,
	onItemClick: (Book) -> Unit,
) {

	val uiState by stateFlow.collectAsStateWithLifecycle()

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text("Books")
				},
			)
		}
	) { innerPadding ->
		if (uiState.error == null) {
			LazyColumn(
				modifier = Modifier.padding(innerPadding),
				content = {
					if (uiState.books.isNotEmpty()) {

						items(
							uiState.books.size,
							{ index -> uiState.books[index].hashCode() }
						) { index ->
							val book = uiState.books[index]
							BookRow(
								book = book,
								onItemClicked = onItemClick
							)
						}

					}
				})
		}
	}

}

