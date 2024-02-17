package com.example.suitify.ui.screens.home.models

import javax.annotation.concurrent.Immutable

@Immutable
sealed class UiEvents {
    data object OnBackPressed : UiEvents()
    data object PlayPause : UiEvents()
    data object SeekToNext : UiEvents()
    data object SeekBack : UiEvents()
    data object Backward : UiEvents()
    data object Forward : UiEvents()
    data object FavoriteChange: UiEvents()
    data object CategoryVisibilityChange : UiEvents()
    data object NavigateToDetails : UiEvents()
    data object NavigateToHome : UiEvents()
    data class SelectedMusicChange(val index: Int) : UiEvents()
    data class SeekTo(val position: Float) : UiEvents()
    data class UpdateProgress(val newProgress: Float) : UiEvents()
    data class SearchTextChange(val searchText: String) : UiEvents()
    data class SearchVisibilityChange(val isVisibleChange: Boolean) : UiEvents()
}