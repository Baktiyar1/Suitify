package com.example.suitify.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.suitify.player.services.MusicService
import com.example.suitify.ui.screens.home.HomeScreen
import com.example.suitify.ui.screens.home.HomeViewModel
import com.example.suitify.ui.screens.home.UiEvents
import com.example.suitify.ui.screens.permissions.PermissionScreen
import com.example.suitify.ui.screens.permissions.PermissionViewModel
import com.example.suitify.ui.theme.SuitifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isServiceRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitifyTheme {
                val viewModel: PermissionViewModel = viewModel()
                PermissionScreen(viewModel = viewModel, activity = this@MainActivity)
                if (viewModel.isGrantedPermission.collectAsState().value) {
                    val homeViewModel: HomeViewModel = viewModel()
                    HomeScreen(
                        musics = homeViewModel.musics.collectAsState().value,
                        playlists = homeViewModel.playlists.collectAsState().value,
                        music = homeViewModel.playingMusic.collectAsState().value,
                        progress = homeViewModel.progress,
                        isVisibleSearch = homeViewModel.isVisibleSearch.collectAsState().value,
                        isVisibleCategory = homeViewModel.isVisibleCategory.collectAsState().value,
                        searchText = homeViewModel.searchText.collectAsState().value,
                        onSearchTextChange = { homeViewModel.onUiEvents(UiEvents.SearchTextChange(it)) },
                        onCategoryVisibilityChange = { homeViewModel.onUiEvents(UiEvents.CategoryVisibilityChange) },
                        onNext = { homeViewModel.onUiEvents(UiEvents.SeekToNext) },
                        onProgress = { homeViewModel.onUiEvents(UiEvents.SeekTo(it)) },
                        onStart = { homeViewModel.onUiEvents(UiEvents.PlayPause) },
                        onSearchVisibilityChange = {
                            homeViewModel.onUiEvents(UiEvents.SearchVisibilityChange(it))
                        },
                        onItemClick = {
                            homeViewModel.onUiEvents(UiEvents.SelectedMusicChange(it))
                            startService()
                        },
                    )
                }
            }
        }
    }

    private fun startService() {
        if (!isServiceRunning) {
            startForegroundService(Intent(this, MusicService::class.java))
            isServiceRunning = true
        }
    }
}