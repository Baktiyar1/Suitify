package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataMusic
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import javax.inject.Inject

class MapMusicFromDataToCache @Inject constructor(private val mapSavedStatus: Mapper<DataSavedStatus, CacheSavedStatus>) :
    Mapper<DataMusic, CacheMusic> {
    override fun map(from: DataMusic) = from.run {
        CacheMusic(
            musicId = musicId.toString(),
            title = title,
            executor = executor,
            duration = duration,
            iconId = iconId,
            isPlaying = isPlaying,
            isFavorite = isFavorite,
            savedStatus = mapSavedStatus.map(savedStatus)
        )
    }
}