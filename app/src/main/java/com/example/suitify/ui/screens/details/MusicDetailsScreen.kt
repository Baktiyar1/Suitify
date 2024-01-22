package com.example.suitify.ui.screens.details

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.suitify.R
import com.example.suitify.models.Music
import com.example.suitify.ui.screens.home.HomeViewModel
import com.example.suitify.ui.theme.AppPrimaryColor
import com.example.suitify.ui.theme.AppSecondaryColor
import com.example.suitify.ui.theme.AuthorTextColor
import com.example.suitify.ui.theme.ImageStyleTheme
import com.example.suitify.ui.theme.MainTextColor
import com.example.suitify.ui.theme.MusicButtons
import com.example.suitify.ui.theme.MusicProgressText
import com.example.suitify.ui.theme.ProgressColor
import com.example.suitify.ui.theme.ProgressTrackColor
import com.example.suitify.ui.theme.TextStyleTheme
import com.example.suitify.ui.theme.dp10
import com.example.suitify.ui.theme.dp16
import com.example.suitify.ui.theme.dp18
import com.example.suitify.ui.theme.dp180
import com.example.suitify.ui.theme.dp20
import com.example.suitify.ui.theme.dp24
import com.example.suitify.ui.theme.dp28
import com.example.suitify.ui.theme.dp32
import com.example.suitify.ui.theme.dp4
import com.example.suitify.ui.theme.dp42
import com.example.suitify.ui.theme.dp44
import com.example.suitify.ui.theme.dp52
import com.example.suitify.ui.theme.dp62
import com.example.suitify.ui.theme.dp8
import com.example.suitify.ui.theme.sp12
import com.example.suitify.ui.theme.sp16
import com.example.suitify.ui.theme.sp18

@Preview(showBackground = true)
@Composable
fun MusicDetailsScreen(modifier: Modifier = Modifier) {
    val music = Music(
        musicId = 3342,
        title = "Прятки",
        displayName = "QWER",
        data = "POIUY",
        artist = "Хамали $ Наваи",
        uri = Uri.EMPTY,
        duration = 423
    )
    val playlistName = "Fav"
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to AppPrimaryColor, 1.0f to AppSecondaryColor, startY = 0.0f, endY = 500f
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopDetailsMenu(modifier = modifier, music = music, playlistName = playlistName)
        MusicPlayerScreen(modifier = modifier, music = music)
    }
}

@Composable
fun TopDetailsMenu(modifier: Modifier = Modifier, music: Music, playlistName: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dp16, vertical = dp24)
    ) {

        MusicButtons(
            modifier = modifier,
            boxSize = dp44,
            imageSize = dp20,
            imageIcon = R.drawable.arrow_left,
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextStyleTheme(
                modifier = modifier, text = stringResource(
                    id = R.string.tx_playing_from, fletchFromPlayingText(music.isFromPlaying)
                ), size = sp16, color = AuthorTextColor
            )
            TextStyleTheme(
                modifier = modifier, text = playlistName, size = sp18, color = MainTextColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MusicButtons(
            modifier = modifier, boxSize = dp44, imageSize = dp20, imageIcon = R.drawable.menu
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(modifier: Modifier = Modifier, music: Music) {
    val viewModel = viewModel<HomeViewModel>()
    Column(
        modifier = modifier.padding(horizontal = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.weight(1f))

        ImageStyleTheme(
            modifier = modifier
                .size(dp180)
                .shadow(elevation = dp16, shape = RoundedCornerShape(dp32)),
            painter = painterResource(id = music.defaultIconId)
        )

        Spacer(modifier = modifier.weight(1f))

        Row(
            modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = modifier) {
                TextStyleTheme(
                    modifier = modifier.padding(top = dp8),
                    text = music.title,
                    size = sp16,
                    color = MainTextColor
                )
                TextStyleTheme(
                    modifier = modifier.padding(top = dp8),
                    text = music.artist,
                    size = sp12,
                    color = AuthorTextColor
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            val isFavoriteImage = remember { mutableStateOf(music.isFavorite) }

            Image(
                modifier = modifier
                    .size(dp24)
                    .clickable(onClick = {
                        music.isFavorite = !music.isFavorite
                        isFavoriteImage.value = music.isFavorite
                    }),
                painter = fetchFavoritePainter(isFavoriteImage.value),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        val sliderPosition = remember { mutableFloatStateOf(value = 5f) }
        val interactionSource = remember { MutableInteractionSource() }

        Slider(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp10),
            value = sliderPosition.floatValue,
            onValueChange = { sliderPosition.floatValue = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            colors = SliderDefaults.colors(
                thumbColor = ProgressColor,
                activeTrackColor = ProgressColor,
                activeTickColor = ProgressColor,
                inactiveTickColor = ProgressTrackColor,
                inactiveTrackColor = ProgressTrackColor,
            ),
            thumb = {
                SliderDefaults.Thumb( //androidx.compose.material3.SliderDefaults
                    interactionSource = interactionSource,
                    thumbSize = DpSize(dp8, dp4),
                    colors = SliderDefaults.colors(thumbColor = ProgressColor)
                )
            },
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = dp16, end = dp16, top = dp16)
        ) {
            MusicProgressText(text = stringResource(id = R.string.tx_default_time))
            Spacer(modifier = modifier.weight(1f))
            MusicProgressText(text = stringResource(id = R.string.tx_default_time))
        }

        Row(
            modifier = modifier.padding(horizontal = dp16, vertical = dp24),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MusicButtons(
                modifier = modifier, boxSize = dp42, imageSize = dp18, imageIcon = R.drawable.group
            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                modifier = modifier,
                boxSize = dp52,
                imageSize = dp24,
                imageIcon = R.drawable.previous
            )

            Spacer(modifier = modifier.weight(1f))

            val isPlaying = remember { viewModel.playingMusic.value.isPlaying }

            MusicButtons(
                modifier = modifier.clickable {  },
                boxSize = dp62,
                imageSize = dp28,
                imageIcon = fetchIsPlayingIdPainter(isFirstImage = isPlaying)
            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                modifier = modifier, boxSize = dp52, imageSize = dp24, imageIcon = R.drawable.next
            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                modifier = modifier,
                boxSize = dp42,
                imageSize = dp18,
                imageIcon = R.drawable.repeate_music
            )
        }
    }
}

@Composable
private fun fetchFavoritePainter(isFirstImage: Boolean): Painter =
    if (isFirstImage) painterResource(R.drawable.favorite)
    else painterResource(R.drawable.not_favorite)

@Composable
private fun fetchIsPlayingIdPainter(isFirstImage: Boolean): Int = if (isFirstImage) R.drawable.play
else R.drawable.pause

@Composable
fun fletchFromPlayingText(isFromPlaylist: Boolean): String = stringResource(
    id = if (isFromPlaylist) R.string.tx_playlist else R.string.tx_main_list
)