package com.polendina.egyptpostalcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.polendina.egyptpostalcode.presentation.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    BottomNavigation (
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier
            .height(90.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavItems.entries.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                icon = {
                    Icon(
                        imageVector = it.imageVector,
                        contentDescription = it.contentDescription,
                    )
                },
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

enum class NavItems(
    val imageVector: ImageVector,
    val contentDescription: String?,
    val route: String
) {
    // TODO: The logic decision of determining the selected icon is yet to be implemented
    HOME(Icons.Outlined.Home, null, Screen.Home.route),
    SEARCH(Icons.Outlined.Search, null, Screen.Search.route),
    SHIPMENT(Icons.Outlined.LocalShipping, null, Screen.Shipment.route),
//    LOCATION(Icons.Outlined.LocationOn, null, Screen.),
//    FAVORITE(Icons.Outlined.FavoriteBorder, null, false)
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomBar() {
    Column (
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomNavigationBar(
            navController = rememberNavController(),
        )
    }
}
