package com.example.data.mappers

import com.example.data.models.DataSavedStatus
import com.example.domain.Mapper
import com.example.domain.models.DomainSavedStatus
import javax.inject.Inject

class MapSavedStatusFromDomainToData @Inject constructor() :
    Mapper<DomainSavedStatus, DataSavedStatus> {
    override fun map(from: DomainSavedStatus) = when (from) {
        DomainSavedStatus.SAVED -> DataSavedStatus.SAVED
        DomainSavedStatus.SAVING -> DataSavedStatus.SAVING
        DomainSavedStatus.NOT_SAVED -> DataSavedStatus.NOT_SAVED
    }
}