package com.example.domain.use_cases

interface UpdateExecutorUseCase {
    suspend fun invoke(id: String, executor: String)
}