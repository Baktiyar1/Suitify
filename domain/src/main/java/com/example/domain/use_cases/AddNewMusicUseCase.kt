package com.example.domain.use_cases

import com.example.domain.models.DomainMusic

interface AddNewMusicUseCase {
    suspend fun invoke(music: DomainMusic)
}