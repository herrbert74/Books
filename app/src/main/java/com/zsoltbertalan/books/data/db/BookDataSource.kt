package com.zsoltbertalan.books.data.db

import com.zsoltbertalan.books.domain.model.Book
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookDataSource(private val realm: Realm) {

	suspend fun saveBookmark(book: Book) {
		realm.write {
			copyToRealm(book.toDbo(), UpdatePolicy.ALL)
		}
	}

	fun getBookmarks(): Flow<List<Book>> {
		return realm.query(BookDbo::class).asFlow().map { result ->
			result.list.map { it.toBook() }
		}
	}

}