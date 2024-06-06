package com.zsoltbertalan.books.data.repository

import com.zsoltbertalan.books.data.db.BookDataSource
import com.zsoltbertalan.books.data.network.BookApi
import com.zsoltbertalan.books.data.network.dto.toBooks
import com.zsoltbertalan.books.domain.model.Book
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://gist.githubusercontent.com/"

val retroFit: Retrofit = Retrofit.Builder()
	.baseUrl(BASE_URL)
	.addConverterFactory(GsonConverterFactory.create())
	.client(OkHttpClient.Builder().build())
	.build()

val libraryService = retroFit.create(BookApi::class.java)

class LibraryRepository(
	private val bookApi: BookApi,
	private val bookDataSource: BookDataSource,
	private val dispatcher: CoroutineDispatcher
) {

	 suspend fun getLibrary(): Flow<List<Book>> {
		 return withContext(dispatcher) {
			flowOf(bookApi.getBooks().toBooks())
		}
	}

	suspend fun saveBookmarks(book: Book) {
		withContext(dispatcher) {
			bookDataSource.saveBookmark(book = book)
		}
	}

	suspend fun getBookmarks(): Flow<List<Book>> {
		return withContext(dispatcher) {
			bookDataSource.getBookmarks()
		}
	}

}
