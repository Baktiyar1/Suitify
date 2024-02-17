package com.example.suitify.ui.screens.details

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.Destination
import com.example.suitify.ui.screens.details.models.MusicDetailsScreenModel
import com.example.suitify.ui.screens.home.HomeViewModel
import com.example.suitify.ui.screens.home.models.UiEvents

private const val MUSIC_DETAILS_SCREEN_ROUTE = "music_details_screen_nav_graph_route"

object MusicDetailsDestination : Destination {
    override fun route(): String = MUSIC_DETAILS_SCREEN_ROUTE
    override fun routeWithArgs(): String = route()
    fun NavGraphBuilder.musicDetailsDestination(homeViewModel: HomeViewModel) {
        composable(route = route()) {
            with(homeViewModel) {
                MusicDetailsScreen(
                    musicDetailsScreenModel = MusicDetailsScreenModel(
                        music = playingMusic.collectAsState().value,
                        playlistName = "Home screen",
                        isPlaying = isPlaying.collectAsState().value
                    ),
                    onBackClick = { onUiEvents(UiEvents.NavigateToHome) },
                    onSeekBack = { onUiEvents(UiEvents.SeekBack) },
                    onPlayOrPause = { onUiEvents(UiEvents.PlayPause) },
                    onSeekToBack = { onUiEvents(UiEvents.SeekToNext) },
                )
            }
        }
    }
}