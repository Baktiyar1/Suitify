package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainPlaylist
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapPlaylistFromUiToDomain @Inject constructor(
    private val mapMusic: Mapper<Music, DomainMusic>,
    private val mapSavedStatus: Mapper<SavedStatus, DomainSavedStatus>
) : Mapper<Playlist, DomainPlaylist> {
    override fun map(from: Playlist) = from.run {
        DomainPlaylist(
            id = id.toString(),
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            isFromPlaying = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}