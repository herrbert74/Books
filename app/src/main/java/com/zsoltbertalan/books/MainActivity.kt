package com.zsoltbertalan.books

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zsoltbertalan.books.ui.component.CustomBottomNavigationBar
import com.zsoltbertalan.books.ui.navigation.NavHostContainer
import com.zsoltbertalan.books.ui.theme.BooksTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			BooksTheme {
				val navController = rememberNavController()

				Scaffold(
					modifier = Modifier.fillMaxSize(),
					bottomBar = {
						CustomBottomNavigationBar(
							navController = navController,
						)
					}
				) { paddingValues ->
					NavHostContainer(navController = navController, paddingValues = paddingValues)
				}

			}
		}
	}
}
