package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapSavedStatusFromDomainToUi @Inject constructor() :
    Mapper<DomainSavedStatus, SavedStatus> {
    override fun map(from: DomainSavedStatus) = when (from) {
        DomainSavedStatus.SAVED -> SavedStatus.SAVED
        DomainSavedStatus.SAVING -> SavedStatus.SAVING
        DomainSavedStatus.NOT_SAVED -> SavedStatus.NOT_SAVED
    }
}