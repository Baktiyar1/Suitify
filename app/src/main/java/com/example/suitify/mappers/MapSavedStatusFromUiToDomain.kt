package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainSavedStatus
import com.example.suitify.models.SavedStatus
import javax.inject.Inject

class MapSavedStatusFromUiToDomain @Inject constructor() : Mapper<SavedStatus, DomainSavedStatus> {
    override fun map(from: SavedStatus) = when (from) {
        SavedStatus.SAVED -> DomainSavedStatus.SAVED
        SavedStatus.SAVING -> DomainSavedStatus.SAVING
        SavedStatus.NOT_SAVED -> DomainSavedStatus.NOT_SAVED
    }
}