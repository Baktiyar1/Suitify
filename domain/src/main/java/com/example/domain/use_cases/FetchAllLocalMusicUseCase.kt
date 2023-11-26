package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic

interface FetchAllLocalMusicUseCase {
    fun invoke(): Result<List<DomainMusic>>
}