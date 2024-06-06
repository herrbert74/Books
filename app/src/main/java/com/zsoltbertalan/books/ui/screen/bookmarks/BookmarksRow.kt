package com.zsoltbertalan.books.ui.screen.bookmarks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.zsoltbertalan.books.domain.model.Book
import com.zsoltbertalan.books.ui.theme.Typography2
import kotlinx.coroutines.Dispatchers

@Composable
fun BookmarksRow(book: Book, onItemClicked: (Book) -> Unit) {

	Card(
		modifier = Modifier
			.padding(vertical = 8.dp, horizontal = 16.dp)
			.clip(RoundedCornerShape(4.dp))
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				//.background(Colors.primaryContainer)
				.wrapContentHeight()
				.fillMaxWidth()
		) {

			Column(modifier = Modifier
				.clickable { onItemClicked(book) }
				.weight(2f)
				//.background(Colors.primaryContainer)
				.padding(vertical = 8.dp, horizontal = 8.dp)
				.testTag("PokemonsRow")
			) {

				Text(
					text = book.title,
					style = Typography2.titleLarge,
				)

				Text(
					text = "Author: ${book.author}",
					style = Typography2.bodyMedium,
				)

				Text(
					text = "Other books: ${book.otherBooks}",
					style = Typography2.bodyMedium,
				)

				Text(
					text = "Stars: ${book.review}",
					style = Typography2.bodyMedium,
				)
				Button(onClick = { onItemClicked(book) }) {
					Text("Bookmark")
				}
			}

			val imageRequest = ImageRequest.Builder(LocalContext.current)
				.data(book.image)
				.dispatcher(Dispatchers.IO)
				.memoryCacheKey(book.image)
				.diskCacheKey(book.image)
				.diskCachePolicy(CachePolicy.ENABLED)
				.memoryCachePolicy(CachePolicy.ENABLED)
				.build()
			AsyncImage(
				model = imageRequest,
				contentDescription = null,
				modifier = Modifier
					//.fillMaxWidth()
					.weight(1f)
					.padding(4.dp)
					.clip(RoundedCornerShape(16.dp))
					.clickable { onItemClicked(book) }
					.testTag("PokemonImage"),
//				placeholder = painterResource(R.drawable.ic_transparent),
//				error = painterResource(id = R.drawable.ic_error),
				contentScale = ContentScale.FillWidth,
			)

		}
	}

}
