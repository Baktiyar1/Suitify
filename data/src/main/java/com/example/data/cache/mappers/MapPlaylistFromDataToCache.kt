package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataPlaylist
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import javax.inject.Inject

class MapPlaylistFromDataToCache @Inject constructor(
    private val mapMusic: Mapper<DataMusic, CacheMusic>,
    private val mapSavedStatus: Mapper<DataSavedStatus, CacheSavedStatus>
) : Mapper<DataPlaylist, CachePlaylist> {
    override fun map(from: DataPlaylist) = from.run {
        CachePlaylist(
            id = id.toString(),
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            isFromPlaying = isFromPlaying,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}