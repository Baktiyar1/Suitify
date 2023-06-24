package com.example.domain.repositories

import com.example.domain.models.DomainMusic
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    fun fletchAllMusic(): Flow<List<DomainMusic>>

}