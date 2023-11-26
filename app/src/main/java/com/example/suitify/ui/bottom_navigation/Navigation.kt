package com.example.suitify.ui.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
//        composable("home") { HomeScreen() }
//        composable("search") { SearchScreen() }
//        composable("your_library") { YourLibraryScreen()}
    }
}