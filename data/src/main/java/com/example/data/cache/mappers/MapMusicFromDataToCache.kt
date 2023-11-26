package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMusicFromDataToCache @Inject constructor(private val mapSavedStatus: Mapper<DataSavedStatus, CacheSavedStatus>) :
    Mapper<DataMusic, CacheMusic> {
    override fun map(from: DataMusic) = from.run {
        CacheMusic(
            musicId = musicId,
            title = title,
            executor = executor,
            displayName = displayName,
            data = data,
            duration = duration,
            uri = uri,
            defaultIconId = defaultIconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}