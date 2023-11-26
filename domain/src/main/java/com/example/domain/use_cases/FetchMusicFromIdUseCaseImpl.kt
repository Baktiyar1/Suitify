package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class FetchMusicFromIdUseCaseImpl(private val repository: MusicRepository) :
    FetchMusicFromIdUseCase {
    override suspend fun invoke(musicId: String): Result<DomainMusic> =
        repository.fetchMusicFromId(musicId = musicId)
}