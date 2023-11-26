package com.example.suitify.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.utils.EMPTY_STRING
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.use_cases.FetchAllLocalMusicUseCase
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAllLocalMusicUseCase: FetchAllLocalMusicUseCase,
    private val mapMusicDomainToUi: Mapper<DomainMusic, Music>,
    private val mapMusicListDomainToUi: Mapper<List<DomainMusic>, List<Music>>
) : ViewModel() {

    private val _searchText = MutableStateFlow(value = EMPTY_STRING)
    val searchText = _searchText.asStateFlow()

    private val _isVolume = MutableStateFlow(value = false)
    val isVolume = _isVolume.asStateFlow()

    private val _isVisibleSearch = MutableStateFlow(value = false)
    val isVisibleSearch = _isVisibleSearch.asStateFlow()

    private var _isVisibleCategory = MutableStateFlow(value = false)
    var isVisibleCategory = _isVisibleCategory.asStateFlow()

    private val _playingMusic = MutableStateFlow(value = Music.unknown())
    val playingMusic = _playingMusic.asStateFlow()

    private val _musics = MutableStateFlow(emptyList<Music>())
    val musics = searchText.combine(_musics) { text, musics ->
        if (text.isBlank()) musics
        else musics.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _musics.value)

    private val _playlists = MutableStateFlow(listOf<Playlist>())
    val playlists = searchText.combine(_playlists) { text, playlists ->
        if (text.isBlank()) playlists else playlists.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _playlists.value)

    init {
        fetchAllLocalMusic()
        if (_musics.value.isNotEmpty()) _playingMusic.tryEmit(_musics.value.first())
    }

    fun fetchAllLocalMusic() {
        val result = fetchAllLocalMusicUseCase.invoke()
        if (result.isFailure) Log.d("Baha", "fetchAllLocalMusic")
        else _musics.tryEmit(mapMusicListDomainToUi.map(result.data ?: emptyList()))
    }

    fun onSearchTextChange(text: String) {
        _searchText.tryEmit(text)
    }

    fun onPlayingChange() {
        _playingMusic.tryEmit(playingMusic.value.copy(isPlaying = !playingMusic.value.isPlaying))
    }

    fun onSearchVisibilityChange(isVisibleChange: Boolean) {
        _isVisibleSearch.tryEmit(isVisibleChange)
    }

    fun onCategoryVisibilityChange() {
        _isVisibleCategory.tryEmit(!_isVisibleCategory.value)
    }
}