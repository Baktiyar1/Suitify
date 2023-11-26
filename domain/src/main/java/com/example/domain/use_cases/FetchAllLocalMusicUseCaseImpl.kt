package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class FetchAllLocalMusicUseCaseImpl(private val repository: MusicRepository) :
    FetchAllLocalMusicUseCase {
    override fun invoke(): Result<List<DomainMusic>> = repository.fletchAllLocalMusic()
}