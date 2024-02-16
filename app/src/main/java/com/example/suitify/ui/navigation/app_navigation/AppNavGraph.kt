package com.example.suitify.ui.navigation.app_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.suitify.ui.screens.details.MusicDetailsDestination.musicDetailsDestination
import com.example.suitify.ui.screens.home.HomeDestination
import com.example.suitify.ui.screens.home.HomeDestination.homeDestination
import com.example.suitify.ui.screens.home.HomeViewModel

@Composable
fun AppNavGraph(navHostController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navController = navHostController, startDestination = HomeDestination.route()) {
        homeDestination(homeViewModel = homeViewModel)
        musicDetailsDestination(homeViewModel = homeViewModel)
    }
}