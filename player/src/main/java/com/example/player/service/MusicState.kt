package com.example.player.service

sealed class MusicState {
    data object Initial : MusicState()
    data class Ready(val duration: Long) : MusicState()
    data class Progress(val progress: Long) : MusicState()
    data class Buffering(val progress: Long) : MusicState()
    data class Playing(val isPlaying: Boolean) : MusicState()
    data class CurrentPlaying(val mediaItemIndex: Int) : MusicState()
}