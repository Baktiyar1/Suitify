package com.example.data.repository

import com.example.domain.RequestState
import com.example.domain.repositories.PlaylistRepository
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor() : PlaylistRepository {
    override fun addNewPlaylist(): RequestState<Unit> {
        TODO("Not yet implemented")
    }

}