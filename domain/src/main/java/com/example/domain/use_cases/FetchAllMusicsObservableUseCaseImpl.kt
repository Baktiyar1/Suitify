package com.example.domain.use_cases

import com.example.domain.common.Result
import com.example.domain.models.DomainMusic
import com.example.domain.repositories.MusicRepository

class FetchAllMusicsObservableUseCaseImpl(private val repository: MusicRepository) :
    FetchAllMusicsObservableUseCase {
    override fun invoke(): Result<List<DomainMusic>> = repository.fetchAllMusicsObservable()
}