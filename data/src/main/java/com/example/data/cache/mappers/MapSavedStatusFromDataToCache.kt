package com.example.data.cache.mappers

import com.example.data.cache.models.CacheSavedStatus
import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import javax.inject.Inject

class MapSavedStatusFromDataToCache @Inject constructor() :
    Mapper<DataSavedStatus, CacheSavedStatus> {
    override fun map(from: DataSavedStatus) = when (from) {
        DataSavedStatus.SAVED -> CacheSavedStatus.SAVED
        DataSavedStatus.SAVING -> CacheSavedStatus.SAVING
        DataSavedStatus.NOT_SAVED -> CacheSavedStatus.NOT_SAVED
    }
}