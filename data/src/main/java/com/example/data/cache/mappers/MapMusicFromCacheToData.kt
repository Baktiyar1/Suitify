package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMusicFromCacheToData @Inject constructor(private val mapSavedStatus: Mapper<CacheSavedStatus, DataSavedStatus>) :
    Mapper<CacheMusic, DataMusic> {
    override fun map(from: CacheMusic) = from.run {
        DataMusic(
            musicId = musicId,
            title = title,
            displayName = displayName,
            data = data,
            executor = executor,
            duration = duration,
            uri = uri,
            defaultIconId = defaultIconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}