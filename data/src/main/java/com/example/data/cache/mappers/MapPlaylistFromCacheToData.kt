package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CachePlaylist
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataPlaylist
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapPlaylistFromCacheToData @Inject constructor(
    private val mapMusic: Mapper<CacheMusic, DataMusic>,
    private val mapSavedStatus: Mapper<CacheSavedStatus, DataSavedStatus>
) : Mapper<CachePlaylist, DataPlaylist> {
    override fun map(from: CachePlaylist) = from.run {
        DataPlaylist(
            id = id,
            name = name,
            iconId = iconId,
            musics = musics.map { mapMusic.map(it) },
            isFavorite = isFavorite,
            isFromPlaying = isFromPlaying,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}