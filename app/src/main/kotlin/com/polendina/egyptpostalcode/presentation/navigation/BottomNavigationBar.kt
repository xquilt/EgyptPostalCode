package com.polendina.egyptpostalcode.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.Icon
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        NavItems.entries.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = currentRoute == navigationItem.route,
                label = {
                    // TODO: Make it so that the default height/icon gets animated as a whoe, not just the label
                    AnimatedVisibility(visible = currentRoute == navigationItem.route) {
                        Text(text = navigationItem.name)
                    }
                },
                icon = {
                    Icon(
                        imageVector = navigationItem.imageVector,
                        contentDescription = navigationItem.contentDescription,
                    )
                },
                onClick = {
                    navController.navigate(navigationItem.route) {
                        navController.graph.startDestinationRoute?.let { route ->
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

private enum class NavItems(
    val imageVector: ImageVector,
    val contentDescription: String?,
    val route: String
) {
    HOME(Icons.Outlined.Home, null, route = Screen.Home.route),
    SEARCH(Icons.Outlined.LocationOn, null, route = Screen.Search.route),
    SHIPMENT(Icons.Outlined.LocalShipping, null, route = Screen.Shipment.route)
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