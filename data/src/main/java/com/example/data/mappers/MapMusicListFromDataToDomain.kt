package com.example.data.mappers

import com.example.data.models.DataMusic
import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import javax.inject.Inject

class MapMusicListFromDataToDomain @Inject constructor(
    private val mapMusic: Mapper<DataMusic, DomainMusic>
) : Mapper<List<DataMusic>, List<DomainMusic>> {

    override fun map(from: List<DataMusic>): List<DomainMusic> = from.map { mapMusic.map(it) }
}