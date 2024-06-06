package com.zsoltbertalan.books.ui.screen.bookmarks

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
fun BookmarksScreen(
	stateFlow: StateFlow<BookmarksViewModel.UiState>,
	onItemClick: (Book) -> Unit,
) {

	val uiState by stateFlow.collectAsStateWithLifecycle()

	Scaffold(
		topBar = {
			TopAppBar(
//				colors = TopAppBarDefaults.topAppBarColors(
//					containerColor = BooksTheme.colorScheme.primaryContainer,
//					titleContentColor = PokedexTheme.colorScheme.onPrimaryContainer,
//				),
				title = {
					Text("Bookmarks")
				},
			)
		}
	) { innerPadding ->
		if (uiState.error == null) {
			LazyColumn(
				modifier = Modifier.padding(innerPadding),
				content = {
					if (uiState.books.isNotEmpty()) {
//						item {
//							Row(
//								modifier = Modifier.padding(
//									top = 8.dp,
//									start = 16.dp,
//									end = 16.dp
//								)
//							) {

						items(
							uiState.books.size,
							{ index -> uiState.books[index].hashCode() }
						) { index ->
							val book = uiState.books[index]
							BookmarksRow(
								book = book,
								onItemClicked = onItemClick
							)
						}

					}
				})
		}
	}

}

