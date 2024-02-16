package com.example.suitify.base

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.core.EMPTY_STRING
import com.example.core.managers.NavigationManager
import com.example.core_ui.R
import com.example.domain.DEFAULT_BOOLEAN
import com.example.domain.DEFAULT_FLOAT
import com.example.domain.DEFAULT_INT
import com.example.domain.DEFAULT_LONG
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.use_cases.FetchAllMusicsObservableUseCase
import com.example.player.service.MusicPlayerEvent
import com.example.player.service.MusicServiceHandler
import com.example.player.service.MusicState
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.ui.screens.details.MusicDetailsDestination
import com.example.suitify.ui.screens.home.HomeDestination
import com.example.suitify.ui.screens.home.models.UiEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(SavedStateHandleSaveableApi::class)
abstract class BaseMusicViewModel(
    private val navigatorManager: NavigationManager,
    private val musicServicesHandler: MusicServiceHandler,
    private val fetchAllMusicsObservableUseCase: FetchAllMusicsObservableUseCase,
    private val mapMusicListDomainToUi: Mapper<List<DomainMusic>, List<Music>>,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    abstract fun onUiEvents(uiEvents: UiEvents)

    var destinationFlow: Flow<Pair<String, Boolean>> = navigatorManager.destinationFlow()

    private var duration by savedStateHandle.saveable { mutableLongStateOf(DEFAULT_LONG) }
    private var mediaItemIndex by savedStateHandle.saveable { mutableIntStateOf(DEFAULT_INT) }

    var progress by savedStateHandle.saveable { mutableFloatStateOf(DEFAULT_FLOAT) }

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _searchText = MutableStateFlow(value = EMPTY_STRING)
    val searchText = _searchText.asStateFlow()

    private val _isVisibleSearch = MutableStateFlow(value = false)
    val isVisibleSearch = _isVisibleSearch.asStateFlow()

    private val _playingMusic = MutableStateFlow(value = Music.unknown())
    val playingMusic = _playingMusic.asStateFlow()

    private val _isServiceRunning = MutableStateFlow(DEFAULT_BOOLEAN)
    val isServiceRunning = _isServiceRunning.asStateFlow()

    private val _isBottomSheetVisibility = MutableStateFlow(value = true)
    val isBottomSheetVisibility = _isBottomSheetVisibility.asStateFlow()

    private val _musics = MutableStateFlow(emptyList<Music>())
    val musics: StateFlow<List<Music>> = _searchText.combine(_musics) { text, musics ->
        val filterMusic = if (text.isBlank()) musics
        else musics.filter { it.doesMatchSearchQuery(text) }
        setMediaItems(filterMusic)
        filterMusic
    }.stateIn(viewModelScope, SharingStarted.Lazily, _musics.value)

    private val _playlists = MutableStateFlow(startPlaylist)
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
                    is MusicState.Playing -> _isPlaying.tryEmit(it.isPlaying)
                    is MusicState.Progress -> calculateProgressValue(it.progress)
                    is MusicState.Ready -> duration = it.duration
                    is MusicState.CurrentPlaying -> {
                        mediaItemIndex = it.mediaItemIndex
                        _playingMusic.tryEmit(musics.value[mediaItemIndex])
                    }
                }
            }
        }
    }

    protected fun onBaseUiEvents(uiEvents: UiEvents) = viewModelScope.launch {
        when (uiEvents) {
            UiEvents.PlayPause -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.PlayPause)
            UiEvents.Backward -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.Backward)
            UiEvents.Forward -> musicServicesHandler.onPlayerEvents(MusicPlayerEvent.Forward)
            UiEvents.FavoriteChange -> _playingMusic.tryEmit(_playingMusic.value.copy(isFavorite = !_playingMusic.value.isFavorite))
            UiEvents.NavigateToDetails -> navigate(isHomeScreen = false)
            UiEvents.NavigateToHome -> navigate(isHomeScreen = true)
            is UiEvents.SearchTextChange -> _searchText.tryEmit(uiEvents.searchText)
            is UiEvents.SearchVisibilityChange -> _isVisibleSearch.tryEmit(uiEvents.isVisibleChange)
            UiEvents.SeekBack -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.SeekBack(lastIndex = musics.value.size - 1)
            )

            is UiEvents.SeekTo -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.SeekTo(seekToPosition = ((duration / uiEvents.position) / 100f).toLong())
            )

            UiEvents.SeekToNext -> {
                val newMusics = musics.value
                val isLastIndex = mediaItemIndex < newMusics.size - 1
                _playingMusic.tryEmit(newMusics[if (!isLastIndex) DEFAULT_INT else ++mediaItemIndex])
                musicServicesHandler.onPlayerEvents(
                    if (isLastIndex) MusicPlayerEvent.SeekToNext
                    else MusicPlayerEvent.SelectedMusicChange(DEFAULT_INT)
                )
            }

            is UiEvents.SelectedMusicChange -> {
                changeIsServiceRunning()
                musicServicesHandler.onPlayerEvents(
                    MusicPlayerEvent.SelectedMusicChange(selectedMusicIndex = uiEvents.index)
                )
            }

            is UiEvents.UpdateProgress -> musicServicesHandler.onPlayerEvents(
                MusicPlayerEvent.UpdateProgress(newProgress = uiEvents.newProgress)
            )

            else -> Unit
        }
    }

    private fun setMediaItems(musics: List<Music>) {
        musics.map { music ->
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
    }

    private fun loadMusicData() {
        viewModelScope.launch {
            val result = fetchAllMusicsObservableUseCase.invoke()
            if (result.isSuccess)
                _musics.tryEmit(mapMusicListDomainToUi.map(from = result.data ?: emptyList()))
            if (_musics.value.isNotEmpty()) _playingMusic.tryEmit(_musics.value[0])
            setMediaItems(musics = musics.value)
        }
    }

    private fun changeIsServiceRunning() {
        _isServiceRunning.tryEmit(!_isServiceRunning.value)
    }

    protected fun changeBottomSheetVisibility(isBottomSheetVisibility: Boolean) {
        _isBottomSheetVisibility.tryEmit(isBottomSheetVisibility)
    }

    private fun navigate(isHomeScreen: Boolean) {
        navigatorManager.navigateTo(
            destination = if (isHomeScreen) HomeDestination.route() else MusicDetailsDestination.route(),
            isClearBackStack = isHomeScreen
        )
        changeBottomSheetVisibility(isBottomSheetVisibility = isHomeScreen)
    }

    companion object {
        private const val ADD_PLAYLIST_ID = "ADD_PLAYLIST_ID"
        private const val ADD_PLAYLIST_NAME = "Add playlist"
        private val startPlaylist = listOf(
            Playlist(
                id = ADD_PLAYLIST_ID,
                name = ADD_PLAYLIST_NAME,
                iconId = R.drawable.default_playlist,
                musics = emptyList(),
            )
        )
    }
}