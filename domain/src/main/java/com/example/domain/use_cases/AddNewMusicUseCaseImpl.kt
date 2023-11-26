package com.example.domain.use_cases

import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class AddNewMusicUseCaseImpl(private val repository: MusicRepository) : AddNewMusicUseCase {
    override suspend fun invoke(music: DomainMusic) = repository.addNewMusic(music = music)
}