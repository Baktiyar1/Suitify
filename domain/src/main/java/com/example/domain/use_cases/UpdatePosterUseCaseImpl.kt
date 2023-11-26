package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class UpdatePosterUseCaseImpl(private val repository: MusicRepository) : UpdatePosterUseCase {
    override suspend fun invoke(id: String, iconId: Int) =
        repository.updatePoster(id = id, iconId = iconId)
}