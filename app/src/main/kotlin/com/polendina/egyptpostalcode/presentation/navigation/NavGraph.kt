package com.polendina.egyptpostalcode.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.polendina.egyptpostalcode.SearchScreen
import com.polendina.egyptpostalcode.presentation.homescreen.components.HomeScreen

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