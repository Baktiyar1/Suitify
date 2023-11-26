package com.example.suitify.mappers

import com.example.domain.base.Mapper
import com.example.domain.models.DomainMusic
import com.example.suitify.models.Music
import javax.inject.Inject

class MapMusicListFromDomainToUi @Inject constructor(
    private val mapMusic: Mapper<DomainMusic, Music>
) : Mapper<List<DomainMusic>, List<Music>> {

    override fun map(from: List<DomainMusic>): List<Music> = from.map { mapMusic.map(it) }
}