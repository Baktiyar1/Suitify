package com.example.suitify.ui.screens.home

import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.Destination
import com.example.suitify.ui.screens.home.models.HomeUiStateModel
import com.example.suitify.ui.screens.home.models.UiEvents

private const val HOME_SCREEN_ROUTE = "home_screen_nav_graph_route"

object HomeDestination : Destination {
    override fun route(): String = HOME_SCREEN_ROUTE
    override fun routeWithArgs(): String = route()
    fun NavGraphBuilder.homeDestination(homeViewModel: HomeViewModel) {
        composable(route = route()) {
            with(homeViewModel) {
                HomeScreen(
                    homeUiStateModel = HomeUiStateModel(
                        musics = musics.collectAsState().value,
                        playlists = playlists.collectAsState().value,
                        musicId = playingMusic.collectAsState().value.musicId,
                        progress = progress,
                        isPlaying = isPlaying.collectAsState().value,
                        isVisibleSearch = isVisibleSearch.collectAsState().value,
                        isVisibleCategory = isVisibleCategory.collectAsState().value,
                        searchText = searchText.collectAsState().value
                    ),
                    onFavoriteClick = { onUiEvents(UiEvents.FavoriteChange(it)) },
                    onSearchTextChange = { onUiEvents(UiEvents.SearchTextChange(it)) },
//                    onCategoryVisibilityChange = { onUiEvents(UiEvents.CategoryVisibilityChange) },
                    onSearchVisibilityChange = { onUiEvents(UiEvents.SearchVisibilityChange(it)) },
                    onItemClick = { onUiEvents(UiEvents.SelectedMusicChange(it)) },
                    onPlayOrPause = { onUiEvents(UiEvents.PlayPause) },
                )
            }
        }
    }
}