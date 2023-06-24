package com.example.suitify.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import com.example.suitify.R
import com.example.suitify.models.Playlist
import com.example.suitify.ui.theme.MainTextColor
import com.example.suitify.ui.theme.MusicItemGradientFirstColor
import com.example.suitify.ui.theme.MusicItemGradientSecondColor
import com.example.suitify.ui.theme.PlaylistItemBackground
import com.example.suitify.ui.theme.dp05
import com.example.suitify.ui.theme.dp12
import com.example.suitify.ui.theme.dp16
import com.example.suitify.ui.theme.dp21
import com.example.suitify.ui.theme.dp26
import com.example.suitify.ui.theme.dp56
import com.example.suitify.ui.theme.dp8
import com.example.suitify.ui.theme.sp11

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier, playlistList: List<Playlist>) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.padding(dp8)) {
            items(items = playlistList) { data -> PlaylistItem(modifier = modifier, data) }
        }
    }
}

@Composable
fun PlaylistItem(modifier: Modifier = Modifier, playlist: Playlist) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(dp8)
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
            .background(
                brush = Brush.verticalGradient(
                    0.0f to MusicItemGradientFirstColor,
                    1.0f to MusicItemGradientSecondColor,
                    startY = 0.0f,
                    endY = 250.0f
                )
            )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(dp05)
                .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
                .background(color = PlaylistItemBackground)
                .clickable(onClick = {})
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = modifier
                        .size(dp56)
                        .clip(shape = RoundedCornerShape(dp26)),
                    painter = painterResource(id = playlist.iconId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = playlist.name,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = dp21, bottom = dp21, start = dp12, end = dp12),
                    style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
                    fontSize = sp11,
                    color = MainTextColor,
                    maxLines = 1
                )
            }
        }
    }
}