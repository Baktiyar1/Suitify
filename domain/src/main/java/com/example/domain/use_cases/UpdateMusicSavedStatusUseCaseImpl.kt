package com.example.domain.use_cases

import com.example.domain.models.DomainSavedStatus
import com.example.domain.repositories.MusicRepository

class UpdateMusicSavedStatusUseCaseImpl(private val repository: MusicRepository) :
    UpdateMusicSavedStatusUseCase {
    override suspend fun invoke(id: String, savedStatus: DomainSavedStatus) =
        repository.updateMusicSavedStatus(id = id, savedStatus = savedStatus)
}