package com.example.domain.use_cases

import com.example.domain.models.DomainMusic

interface SaveMusicsUseCase {
    suspend fun invoke(musics: List<DomainMusic>)
}