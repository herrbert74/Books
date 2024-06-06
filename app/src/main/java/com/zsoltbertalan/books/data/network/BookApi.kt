package com.zsoltbertalan.books.data.network

import com.zsoltbertalan.books.data.network.dto.LibraryDto
import retrofit2.http.GET

interface BookApi {

	@GET("sebskuse/a57b26640883bd70ee5ac092a5cdbfce/raw/2e6964b4eeaa5203117043d046754b36d8da503d/test_api.json")
	suspend fun getBooks(): LibraryDto

}
