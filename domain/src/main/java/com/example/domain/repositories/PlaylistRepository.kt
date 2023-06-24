package com.example.domain.repositories

import com.example.domain.RequestState

interface PlaylistRepository {

    fun addNewPlaylist(): RequestState<Unit>

}