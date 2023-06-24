package com.example.data.cache.mappers

import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import javax.inject.Inject

class MapSavedStatusFromCacheToData @Inject constructor() :
    Mapper<CacheSavedStatus, DataSavedStatus> {
    override fun map(from: CacheSavedStatus) = when (from) {
        CacheSavedStatus.SAVED -> DataSavedStatus.SAVED
        CacheSavedStatus.SAVING -> DataSavedStatus.SAVING
        CacheSavedStatus.NOT_SAVED -> DataSavedStatus.NOT_SAVED
    }
}