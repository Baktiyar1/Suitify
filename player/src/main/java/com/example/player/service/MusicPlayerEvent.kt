package com.example.player.service

sealed class MusicPlayerEvent {
    data object PlayPause : MusicPlayerEvent()
    data object Backward : MusicPlayerEvent()
    data object SeekToNext : MusicPlayerEvent()
    data object Forward : MusicPlayerEvent()
    data object Stop : MusicPlayerEvent()
    data class SeekBack(val lastIndex: Int) : MusicPlayerEvent()
    data class SeekTo(val seekToPosition: Long) : MusicPlayerEvent()
    data class SelectedMusicChange(val selectedMusicIndex: Int) : MusicPlayerEvent()
    data class UpdateProgress(val newProgress: Float) : MusicPlayerEvent()
}