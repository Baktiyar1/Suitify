package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.domain.models.DomainPlaylist
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapPlaylistFromDomainToUi @Inject constructor(
    private val mapMusic: Mapper<DomainMusic, Music>,
    private val mapSavedStatus: Mapper<DomainSavedStatus, SavedStatus>
) :
    Mapper<DomainPlaylist, Playlist> {
    override fun map(from: DomainPlaylist) = from.run {
        Playlist(
            id = id,
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}