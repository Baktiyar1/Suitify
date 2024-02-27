package com.example.player.service

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.DEFAULT_INT
import com.example.domain.DispatchersProvider
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MusicServiceHandler @Inject constructor(
    private val player: ExoPlayer,
    private val dispatchersProvider: DispatchersProvider
) : Player.Listener {

    init {
        player.addListener(this)
    }

    private val _musicState = MutableStateFlow<MusicState>(MusicState.Initial)
    val musicState = _musicState.asStateFlow()

    private var job: Job? = null

    override fun onPlaybackStateChanged(playbackState: Int) {
        _musicState.tryEmit(
            when (playbackState) {
                ExoPlayer.STATE_BUFFERING -> MusicState.Buffering(progress = player.contentPosition)
                ExoPlayer.STATE_READY -> MusicState.Ready(duration = player.duration)
                else -> return
            }
        )
    }

    @DelicateCoroutinesApi
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _musicState.tryEmit(MusicState.Playing(isPlaying = isPlaying))
        _musicState.tryEmit(MusicState.CurrentPlaying(mediaItemIndex = player.currentMediaItemIndex))
        if (isPlaying) GlobalScope.launch(dispatchersProvider.main()) { startProgressUpdate() }
        else stopProgressUpdate()
    }

//    fun addMediaItem(mediaItem: MediaItem) {
//        player.setMediaItem(mediaItem)
//        player.prepare()
//    }

    fun setMediaItemList(mediaItems: List<MediaItem>) {
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    suspend fun onPlayerEvents(musicPlayerEvent: MusicPlayerEvent) {
        when (musicPlayerEvent) {
            MusicPlayerEvent.Backward -> player.seekBack()
            MusicPlayerEvent.Forward -> player.seekForward()
            MusicPlayerEvent.SeekToNext -> selectMusic(selectedMusicIndex = player.currentMediaItemIndex.let { if (it < player.mediaItemCount - 1) it + 1 else DEFAULT_INT })
            MusicPlayerEvent.PlayPause -> playOrPause()
            MusicPlayerEvent.Stop -> stopProgressUpdate()
            is MusicPlayerEvent.SeekBack -> selectMusic(selectedMusicIndex = player.currentMediaItemIndex.let { if (it > 0) player.currentMediaItemIndex - 1 else musicPlayerEvent.lastIndex })
            is MusicPlayerEvent.SeekTo -> player.seekTo(musicPlayerEvent.seekToPosition)
            is MusicPlayerEvent.UpdateProgress -> player.seekTo((player.duration * musicPlayerEvent.newProgress).toLong())
            is MusicPlayerEvent.SelectedMusicChange -> when (musicPlayerEvent.selectedMusicIndex) {
                player.currentMediaItemIndex -> playOrPause()
                else -> selectMusic(selectedMusicIndex = musicPlayerEvent.selectedMusicIndex)
            }
        }
    }

    private suspend fun selectMusic(selectedMusicIndex: Int) {
        player.seekToDefaultPosition(selectedMusicIndex)
        _musicState.tryEmit(MusicState.Playing(isPlaying = true))
        player.playWhenReady = true
        startProgressUpdate()
    }

    private suspend fun playOrPause() = if (player.isPlaying) {
        player.pause()
        stopProgressUpdate()
    } else {
        player.play()
        _musicState.tryEmit(MusicState.Playing(isPlaying = true))
        startProgressUpdate()
    }

    private suspend fun startProgressUpdate() = job.run {
        while (player.isPlaying) {
            delay(DELAY_TIME_MILLIS)
            _musicState.tryEmit(MusicState.Progress(player.currentPosition))
            _musicState.tryEmit(MusicState.CurrentPlaying(mediaItemIndex = player.currentMediaItemIndex))
        }
    }

    private fun stopProgressUpdate() {
        job?.cancel()
        _musicState.tryEmit(MusicState.Playing(isPlaying = false))
    }

    companion object {
        private const val DELAY_TIME_MILLIS = 500L
    }
}