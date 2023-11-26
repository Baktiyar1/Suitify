package com.example.data.cache.mappers

import com.example.data.cache.models.CacheMusic
import com.example.data.models.DataMusic
import com.example.domain.base.Mapper
import javax.inject.Inject

class MapMusicListFromCacheToData @Inject constructor(
    private val mapMusic: Mapper<CacheMusic, DataMusic>
) : Mapper<List<CacheMusic>, List<DataMusic>> {

    override fun map(from: List<CacheMusic>): List<DataMusic> = from.map { mapMusic.map(it) }
}