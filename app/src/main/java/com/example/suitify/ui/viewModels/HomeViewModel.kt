package com.example.suitify.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isVisibleSearch = MutableStateFlow(false)
    val isVisibleSearch = _isVisibleSearch.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _isVolume = MutableStateFlow(false)
    val isVolume = _isVolume.asStateFlow()

    private val _isVisibleCategory = MutableStateFlow(false)
    val isVisibleCategory = _isVisibleCategory.asStateFlow()

    private val _musics = MutableStateFlow(listOf<Music>())
    val musics = searchText.combine(_musics) { text, musics ->
        if (text.isBlank()) musics
        else musics.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _musics.value)

    private val _playlists = MutableStateFlow(listOf<Playlist>())
    val playlists = searchText.combine(_playlists) { text, playlists ->
        if (text.isBlank()) playlists
        else playlists.filter { it.doesMatchSearchQuery(text) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _playlists.value)

    private val _music = MutableStateFlow(value = Music.unknown())
    val music = _music.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onPlayingChange() {
        _isPlaying.value = !_isPlaying.value
    }

    fun onSearchVisibilityChange() {
        _isVisibleSearch.value = !_isVisibleSearch.value
    }

    fun onCategoryVisibilityChange() {
        _isVisibleCategory.value = !_isVisibleCategory.value
    }

    fun testNewMusics() {
        _musics.value = listOf(
            Music(title = "Прятки", executor = "Хамали $ Наваи", duration = 423.4f),
            Music(title = "Natural", executor = "Imagin Dragons", duration = 182.4f),
            Music(title = "Believer", executor = "Imagin Dragons", duration = 474.4f),
            Music(title = "GO!", executor = "NEFFEX", duration = 952.4f),
            Music(title = "GO!", executor = "NEFFEX", duration = 952.4f),
        )
        _music.value = _musics.value[0]
    }

    fun testNewPlaylists() {
        _playlists.value = listOf(
            Playlist(name = "Fav", musics = listOf()),
            Playlist(name = "ADdv", musics = listOf()),
            Playlist(name = "OLjdsj", musics = listOf()),
        )
    }
}