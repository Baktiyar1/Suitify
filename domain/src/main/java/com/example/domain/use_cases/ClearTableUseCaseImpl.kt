package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class ClearTableUseCaseImpl(private val repository: MusicRepository) : ClearTableUseCase {
    override suspend fun invoke() = repository.clearFavoriteTable()
}