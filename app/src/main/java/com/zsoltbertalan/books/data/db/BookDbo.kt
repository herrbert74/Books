package com.zsoltbertalan.books.data.db

import com.zsoltbertalan.books.domain.model.Book
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey

class BookDbo() : RealmObject {

	constructor(
		image: String = "",
		title: String = "",
		author: String = "",
		review: String = "",
		otherBooks: String = "",
	) : this() {
		this.image = image
		this.title = title
		this.author = author
		this.review = review
		this.otherBooks = otherBooks
	}

	@PrimaryKey
	var id: RealmUUID = RealmUUID.random()
	var image: String = ""
	var title: String = ""
	var author: String = ""
	var review: String = ""
	var otherBooks: String = ""

}

fun BookDbo.toBook():Book = Book(image, title, author, review, otherBooks)

fun Book.toDbo():BookDbo = BookDbo(image, title, author, review, otherBooks)
