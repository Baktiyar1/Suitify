package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic

interface FetchMusicFromIdUseCase {
    suspend fun invoke(musicId: String): Result<DomainMusic>
}