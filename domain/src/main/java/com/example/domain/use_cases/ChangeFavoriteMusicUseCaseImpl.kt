package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class ChangeFavoriteMusicUseCaseImpl(private val repository: MusicRepository) : ChangeFavoriteMusicUseCase {
    override suspend fun invoke(musicId: Long) = repository.changeFavoritesMusic(musicId = musicId)
}