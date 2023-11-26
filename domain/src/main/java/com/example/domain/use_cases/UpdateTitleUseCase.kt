package com.example.domain.use_cases

interface UpdateTitleUseCase {
    suspend fun invoke(id: String, title: String)
}