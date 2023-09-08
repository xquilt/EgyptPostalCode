package com.polendina.egyptpostalcode.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.polendina.egyptpostalcode.SearchScreen
import com.polendina.egyptpostalcode.presentation.homescreen.components.HomeScreen

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Shipment: Screen("shipment_screen")
    object Provinces: Screen("provinces_screen")
}

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen(activeState = true)
        }
        composable(Screen.Shipment.route) { }
        composable(Screen.Provinces.route) { }
    }
}