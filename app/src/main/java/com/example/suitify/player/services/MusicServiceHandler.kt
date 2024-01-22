package com.example.suitify.player.services

import android.util.Log
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.DEFAULT_LONG
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
        _musicState.value = when (playbackState) {
            ExoPlayer.STATE_BUFFERING ->  MusicState.Buffering(progress = player.contentPosition)
            ExoPlayer.STATE_READY ->  MusicState.Ready(duration = player.duration)
            else -> return
        }
    }

    @DelicateCoroutinesApi
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _musicState.value = MusicState.Playing(isPlaying = isPlaying)
        _musicState.value = MusicState.CurrentPlaying(player.currentMediaItemIndex)
        if (isPlaying) GlobalScope.launch(dispatchersProvider.main()) { startProgressUpdate() }
        else stopProgressUpdate()
    }

    fun addMediaItem(mediaItem: MediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    fun setMediaItemList(mediaItems: List<MediaItem>) {
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    suspend fun onPlayerEvents(
        musicPlayerEvent: MusicPlayerEvent,
        selectedMusicIndex: Int = DEFAULT_INT,
        seekToPosition: Long = DEFAULT_LONG
    ) {
        when (musicPlayerEvent) {
            MusicPlayerEvent.Backward -> player.seekBack()
            MusicPlayerEvent.Forward -> player.seekForward()
            MusicPlayerEvent.SeekToNext -> player.seekToNext()
            MusicPlayerEvent.PlayPause -> playOrPause()
            MusicPlayerEvent.SeekTo -> player.seekTo(seekToPosition)
            MusicPlayerEvent.Stop -> stopProgressUpdate()
            is MusicPlayerEvent.UpdateProgress -> player.seekTo((player.duration * musicPlayerEvent.newProgress).toLong())
            MusicPlayerEvent.SelectedMusicChange -> {
                when (selectedMusicIndex) {
                    player.currentMediaItemIndex -> playOrPause()
                    else -> {
                        player.seekToDefaultPosition(selectedMusicIndex)
                        _musicState.value = MusicState.Playing(isPlaying = true)
                        player.playWhenReady = true
                        startProgressUpdate()
                    }
                }
            }
        }
    }

    private suspend fun playOrPause() {
        if (player.isPlaying) {
            player.pause()
            stopProgressUpdate()
        } else {
            player.play()
            Log.d("Baha", "player.play: ${player.isPlaying}")
            _musicState.value = MusicState.Playing(isPlaying = true)
            startProgressUpdate()
        }
    }

    private suspend fun startProgressUpdate() = job.run {
        while (true) {
            delay(DELAY_TIME_MILLIS)
            _musicState.value = MusicState.Progress(player.currentPosition)
        }
    }

    private fun stopProgressUpdate() {
        job?.cancel()
        _musicState.value = MusicState.Playing(isPlaying = false)
    }

    companion object {
        private const val DEFAULT_INT = -1
        private const val DELAY_TIME_MILLIS = 500L
    }
}