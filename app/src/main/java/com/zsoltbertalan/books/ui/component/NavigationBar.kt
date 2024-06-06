package com.zsoltbertalan.books.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zsoltbertalan.books.R
import com.zsoltbertalan.books.ui.navigation.Destination

@Composable
fun CustomBottomNavigationBar(
	modifier: Modifier = Modifier,
	navController: NavHostController,
	itemList: List<Destination> = Destination.entries,
) {

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route

	CustomNavigationBar(
		modifier = modifier,
	) {
		itemList.forEach { destination ->
			CustomNavigationBarItem(
				selected = currentRoute == destination.route,
				label = {
					Text(
						style = MaterialTheme.typography.bodySmall,
						text = stringResource(destination.titleId)
					)
				},
				icon = if (destination == Destination.BOOKS)
					R.drawable.baseline_menu_book_24
				else
					R.drawable.baseline_bookmark_24,
				onClick = {
					navController.navigate(destination.route) {
						popUpTo(navController.graph.findStartDestination().id) {
							saveState = true
						}
						launchSingleTop = true
						restoreState = true
					}
				},
			)
		}
	}
}

@Composable
fun CustomNavigationBar(
	modifier: Modifier = Modifier,
	content: @Composable RowScope.() -> Unit
) {
	NavigationBar(
		containerColor = MaterialTheme.colorScheme.surface,
		modifier = modifier,
		content = content
	)
}

@Composable
fun RowScope.CustomNavigationBarItem(
	selected: Boolean,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	@DrawableRes icon: Int,
	label: @Composable (() -> Unit)? = null,
	alwaysShowLabel: Boolean = true,
	onClick: () -> Unit
) {
	NavigationBarItem(
		selected = selected,
		modifier = modifier,
		enabled = enabled,
		label = label,
		alwaysShowLabel = alwaysShowLabel,
		colors = NavigationBarItemDefaults.colors(
			selectedTextColor = MaterialTheme.colorScheme.onSurface,
			unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
			unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant
		),
		icon = {
			Icon(
				painter = painterResource(id = icon),
				contentDescription = "",
				modifier = Modifier.height(20.dp),
			)
		},
		onClick = onClick
	)
}
