package com.example.domain.use_cases

interface DeleteMusicUseCase {
    suspend fun invoke(id: String)
}