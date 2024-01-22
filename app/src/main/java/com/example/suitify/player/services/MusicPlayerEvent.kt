package com.example.suitify.player.services

sealed class MusicPlayerEvent {
    data object PlayPause : MusicPlayerEvent()
    data object SelectedMusicChange : MusicPlayerEvent()
    data object Backward : MusicPlayerEvent()
    data object SeekToNext : MusicPlayerEvent()
    data object Forward : MusicPlayerEvent()
    data object SeekTo : MusicPlayerEvent()
    data object Stop : MusicPlayerEvent()
    data class UpdateProgress(val newProgress: Float) : MusicPlayerEvent()
}