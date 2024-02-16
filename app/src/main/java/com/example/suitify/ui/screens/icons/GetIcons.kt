package com.example.suitify.ui.screens.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.core_ui.R

@Composable
fun fetchFavoritePainter(isFavorite: Boolean): Painter =
    painterResource(id = if (isFavorite) R.drawable.favorite else R.drawable.not_favorite)

@Composable
fun fetchIsPlayingPainter(isPlaying: Boolean): Painter =
    painterResource(id = if (isPlaying) R.drawable.pause else R.drawable.play)

//@Composable
//fun fetchVolumePainter(isVolumeActive: Boolean): Painter =
//    painterResource(id = if (isVolumeActive) R.drawable.volume_off else R.drawable.volume)

@Composable
fun fetchCategoryPainter(isMusic: Boolean): Int =
    if (isMusic) R.drawable.music else R.drawable.playlist