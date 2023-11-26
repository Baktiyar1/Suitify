package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class UpdateTitleUseCaseImpl(private val repository: MusicRepository) : UpdateTitleUseCase {
    override suspend fun invoke(id: String, title: String) =
        repository.updateTitle(id = id, title = title)
}