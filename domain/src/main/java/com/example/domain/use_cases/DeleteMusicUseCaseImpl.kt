package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class DeleteMusicUseCaseImpl(private val repository: MusicRepository) : DeleteMusicUseCase {
    override suspend fun invoke(id: String) = repository.deleteMusic(id = id)
}