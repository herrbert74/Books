package com.zsoltbertalan.books.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zsoltbertalan.books.data.db.BookDataSource
import com.zsoltbertalan.books.data.db.BookDbo
import com.zsoltbertalan.books.data.repository.LibraryRepository
import com.zsoltbertalan.books.data.repository.libraryService
import com.zsoltbertalan.books.ui.navigation.Destination.BOOKMARKS
import com.zsoltbertalan.books.ui.navigation.Destination.BOOKS
import com.zsoltbertalan.books.ui.screen.bookmarks.BookmarksScreen
import com.zsoltbertalan.books.ui.screen.bookmarks.BookmarksViewModel
import com.zsoltbertalan.books.ui.screen.books.BooksScreen
import com.zsoltbertalan.books.ui.screen.books.BooksViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.Dispatchers

@Composable
fun NavHostContainer(
	navController: NavHostController,
	paddingValues: PaddingValues,
) {
	val realmConfig = RealmConfiguration.Builder(
		schema = setOf(BookDbo::class)
	).build()

	val realm = Realm.open(realmConfig)

	val bookDataSource = BookDataSource(realm)
	val repo = LibraryRepository(libraryService, bookDataSource, Dispatchers.IO)
	val booksViewModel = BooksViewModel(repo)
	val bookmarksViewModel = BookmarksViewModel(repo)

	NavHost(
		navController = navController,
		startDestination = BOOKS.route,
		modifier = Modifier.padding(paddingValues),
		builder = {
			composable(BOOKS.route){
				BooksScreen(stateFlow = booksViewModel.state) {
					booksViewModel.saveBookmark(it)
				}
			}
			composable(BOOKMARKS.route){
				BookmarksScreen(stateFlow = bookmarksViewModel.state) {

				}
			}
		}
	)
}