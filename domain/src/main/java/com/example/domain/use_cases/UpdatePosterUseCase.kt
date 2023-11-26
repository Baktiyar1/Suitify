package com.example.domain.use_cases

interface UpdatePosterUseCase {
    suspend fun invoke(id: String, iconId: Int)
}