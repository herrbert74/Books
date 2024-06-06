package com.zsoltbertalan.books.data.network.dto

import com.zsoltbertalan.books.domain.model.Book

data class LibraryDto(
	val `data`: List<Data>? = null,
	val includes: List<Include>? = null
) {
	data class Data(
		val attributes: Attributes? = null,
		val id: String? = null,
		val relationships: Relationships? = null,
		val type: String? = null
	) {
		data class Attributes(
			val image: String? = null,
			val title: String? = null
		)

		data class Relationships(
			val author: Author? = null,
			val reviews: Reviews? = null
		) {
			data class Author(
				val `data`: Data? = null
			) {
				data class Data(
					val id: String? = null,
					val type: String? = null
				)
			}

			data class Reviews(
				val `data`: Data? = null
			) {
				data class Data(
					val id: String? = null,
					val type: String? = null
				)
			}
		}
	}

	data class Include(
		val attributes: Attributes? = null,
		val id: String? = null,
		val relationships: Relationships? = null,
		val type: String? = null
	) {
		data class Attributes(
			val name: String? = null,
			val stars: String? = null
		)

		data class Relationships(
			val books: Books? = null
		) {
			data class Books(
				val `data`: List<Data?>? = null
			) {
				data class Data(
					val id: String? = null,
					val type: String? = null
				)
			}
		}
	}
}

fun LibraryDto.toBooks(): List<Book> {
	val authors = includes?.filter { it.type == "author" }.orEmpty().associateBy { i -> i.id }
	val reviews = includes?.filter { it.type == "review" }.orEmpty().associateBy { i -> i.id }
	val booksByAuthor = data.orEmpty().groupBy { book -> book.relationships?.author?.data?.id }
	return `data`?.map { book ->
		val authorId = book.relationships?.author?.data?.id
		val otherBooks = booksByAuthor[authorId]?.filter { it.id != book.id }
		Book(
			book.attributes?.title ?: "",
			authors[authorId]?.attributes?.name ?: "",
			book.attributes?.image ?: "",
			reviews[book.relationships?.reviews?.data?.id]?.attributes?.stars ?: "",
			otherBooks?.map { it.attributes?.title }.orEmpty().joinToString(",")
		)
	}.orEmpty()
}
