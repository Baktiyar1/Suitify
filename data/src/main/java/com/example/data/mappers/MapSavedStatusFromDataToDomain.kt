package com.example.data.mappers

import com.example.data.models.DataSavedStatus
import com.example.domain.base.Mapper
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapSavedStatusFromDataToDomain @Inject constructor() :
    Mapper<DataSavedStatus, DomainSavedStatus> {
    override fun map(from: DataSavedStatus) = when (from) {
        DataSavedStatus.SAVED -> DomainSavedStatus.SAVED
        DataSavedStatus.SAVING -> DomainSavedStatus.SAVING
        DataSavedStatus.NOT_SAVED -> DomainSavedStatus.NOT_SAVED
    }
}