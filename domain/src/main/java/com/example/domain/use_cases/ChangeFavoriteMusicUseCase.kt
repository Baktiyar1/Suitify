package com.example.domain.use_cases

interface ChangeFavoriteMusicUseCase {
    suspend fun invoke(musicId: Long)
}