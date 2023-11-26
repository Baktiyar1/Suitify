package com.example.domain.use_cases

import com.example.domain.repositories.MusicRepository

class UpdateExecutorUseCaseImpl(private val repository: MusicRepository) : UpdateExecutorUseCase {
    override suspend fun invoke(id: String, executor: String) =
        repository.updateExecutor(id = id, executor = executor)
}