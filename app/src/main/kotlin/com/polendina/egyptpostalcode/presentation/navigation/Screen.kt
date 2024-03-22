package com.polendina.egyptpostalcode.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Shipment: Screen("shipment_screen")
    object Provinces: Screen("provinces_screen")
    object Contact: Screen("contact_developers")
}