package com.example.suitify.ui.screens.home

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.data.utils.EMPTY_STRING
import com.example.domain.DEFAULT_FLOAT
import com.example.domain.DEFAULT_LONG
import com.example.domain.DEFAULT_TIME_TEXT
import com.example.domain.base.Mapper
import com.example.domain.formatDuration
import com.example.domain.models.DomainMusic
import com.example.domain.use_cases.FetchAllMusicsObservableUseCase
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.player.services.MusicPlayerEvent
import com.example.suitify.player.services.MusicServiceHandler
import com.example.suitify.player.services.MusicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val musicServicesHandler: MusicServiceHandler,
    private val fetchAllMusicsObservableUseCase: FetchAllMusicsObservableUseCase,
    private val mapMusicListDomainToUi: Mapper<List<DomainMusic>, List<Music>>,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var duration by savedStateHandle.saveable { mutableLongStateOf(DEFAULT_LONG) }
    private var progressString by savedStateHandle.saveable { mutableStateOf(DEFAULT_TIME_TEXT) }

    var progress by savedStateHandle.saveable { mutableFloatStateOf(DEFAULT_FLOAT) }

    private val _searchText = MutableStateFlow(value = EMPTY_STRING)
    val searchText = _searchText.asStateFlow()

    private val _isVisibleSearch = MutableStateFlow(value = false)
    val isVisibleSearch = _isVisibleSearch.asStateFlow()

    private var _isVisibleCategory = MutableStateFlow(value = false)
    var isVisibleCategory = _isVisibleCategory.asStateFlow()

    private val _playingMusic = MutableStateFlow(value = Music.unknown())
    val playingMusic = _playingMusic.asStateFlow()

    private val _musics = MutableStateFlow(emptyList<Music>())
    val musics = _searchText.combine(_musics) { text, musics ->
        if (text.isBlank()) musics
        else musics.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _musics.value)

    private val _playlists = MutableStateFlow(listOf<Playlist>())
    val playlists = _searchText.combine(_playlists) { text, playlists ->
        if (text.isBlank()) playlists else playlists.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _playlists.value)

    init {
        loadMusicData()
    }

    init {
        viewModelScope.launch {
            musicServicesHandler.musicState.collectLatest {
                when (it) {
                    is MusicState.Initial -> Unit
                    is MusicState.Buffering -> calculateProgressValue(it.progress)
                    is MusicState.Playing -> playingMusic.value.isPlaying = it.isPlaying
                    is MusicState.Progress -> calculateProgressValue(it.progress)
                    is MusicState.CurrentPlaying -> _playingMusic.tryEmit(musics.value[it.mediaItemIndex])
                    is MusicState.Ready -> duration = it.duration
                }
            }
        }
    }

    fun onUiEvents(uiEvents: UiEvents) = viewModelScope.launch {
        when (uiEvents) {
            UiEvents.PlayPause -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.PlayPause)
            UiEvents.SeekToNext -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.SeekToNext)
            UiEvents.Backward -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.Backward)
            UiEvents.Forward -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.Forward)
            UiEvents.CategoryVisibilityChange -> onCategoryVisibilityChange()
            is UiEvents.SearchTextChange -> _searchText.tryEmit(uiEvents.searchText)
            is UiEvents.SearchVisibilityChange -> _isVisibleSearch.tryEmit(uiEvents.isVisibleChange)
            is UiEvents.SelectedMusicChange -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.SelectedMusicChange,
                selectedMusicIndex = uiEvents.index
            )

            is UiEvents.SeekTo -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.SeekTo,
                seekToPosition = ((duration / uiEvents.position) / 100f).toLong()
            )

            is UiEvents.UpdateProgress -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.UpdateProgress(newProgress = uiEvents.newProgress)
            )
        }
    }

    private fun onCategoryVisibilityChange() {
        _isVisibleCategory.tryEmit(!_isVisibleCategory.value)
    }

    private fun loadMusicData() {
        viewModelScope.launch {
            val result = fetchAllMusicsObservableUseCase.invoke()
            when (result.isSuccess) {
                true -> _musics.tryEmit(
                    mapMusicListDomainToUi.map(from = result.data ?: emptyList())
                )

                false -> Unit
            }
            if (_musics.value.isNotEmpty()) _playingMusic.tryEmit(_musics.value[0])
            setMediaItems()
        }
    }

    private fun setMediaItems() {
        _musics.value.map { music ->
            MediaItem.Builder()
                .setUri(music.uri)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setAlbumArtist(music.artist)
                        .setDisplayTitle(music.title)
                        .setSubtitle(music.displayName)
                        .build()
                )
                .build()
        }.also(musicServicesHandler::setMediaItemList)
    }

    private fun calculateProgressValue(currentProgress: Long) {
        progress = if (currentProgress <= 0) DEFAULT_FLOAT
        else (currentProgress.toFloat() / duration.toFloat() * 100f)
        progressString = formatDuration(currentProgress)
    }
}