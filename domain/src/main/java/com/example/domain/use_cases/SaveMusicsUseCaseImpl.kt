package com.example.domain.use_cases

import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class SaveMusicsUseCaseImpl(private val repository: MusicRepository) : SaveMusicsUseCase {
    override suspend fun invoke(musics: List<DomainMusic>) = repository.saveMusics(musics = musics)
}