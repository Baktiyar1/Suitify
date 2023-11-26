package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class FetchMusicObservableUseCaseImpl(private val repository: MusicRepository) :
    FetchMusicObservableUseCase {
    override fun invoke(musicId: String): Result<DomainMusic> =
        repository.fetchMusicObservable(musicId = musicId)
}