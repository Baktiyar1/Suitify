package com.example.domain.use_cases

import com.example.domain.models.DomainSavedStatus

interface UpdateMusicSavedStatusUseCase {
    suspend fun invoke(id: String, savedStatus: DomainSavedStatus)
}