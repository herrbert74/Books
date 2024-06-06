package com.zsoltbertalan.books.ui.navigation

import androidx.annotation.StringRes
import com.zsoltbertalan.books.R

enum class Destination(
	@StringRes val titleId: Int,
	val route: String
) {
	BOOKS(R.string.books, "books"),
	BOOKMARKS(R.string.bookmarks, "bookmarks"),
}
