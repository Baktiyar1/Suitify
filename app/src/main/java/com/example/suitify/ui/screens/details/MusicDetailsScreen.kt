package com.example.suitify.ui.screens.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import com.example.core_ui.R
import com.example.core_ui.extention.clickableNoRipple
import com.example.core_ui.theme.AppPrimaryColor
import com.example.core_ui.theme.AppSecondaryColor
import com.example.core_ui.theme.AuthorTextColor
import com.example.core_ui.theme.ImageStyleTheme
import com.example.core_ui.theme.MainTextColor
import com.example.core_ui.theme.MusicButtons
import com.example.core_ui.theme.MusicProgressText
import com.example.core_ui.theme.ProgressColor
import com.example.core_ui.theme.ProgressTrackColor
import com.example.core_ui.theme.TextStyleTheme
import com.example.core_ui.theme.dp10
import com.example.core_ui.theme.dp16
import com.example.core_ui.theme.dp180
import com.example.core_ui.theme.dp24
import com.example.core_ui.theme.dp28
import com.example.core_ui.theme.dp4
import com.example.core_ui.theme.dp52
import com.example.core_ui.theme.dp62
import com.example.core_ui.theme.dp8
import com.example.core_ui.theme.sp12
import com.example.core_ui.theme.sp16
import com.example.core_ui.theme.sp18
import com.example.domain.DEFAULT_INT
import com.example.suitify.models.Music
import com.example.suitify.ui.screens.details.models.MusicDetailsScreenModel
import com.example.suitify.ui.screens.details.models.MusicPlayerScreenModel
import com.example.suitify.ui.screens.icons.fetchFavoritePainter
import com.example.suitify.ui.screens.icons.fetchIsPlayingPainter

@Composable
fun MusicDetailsScreen(
    musicDetailsScreenModel: MusicDetailsScreenModel,
    onBackClick: () -> Unit,
    onSeekBack: () -> Unit,
    onPlayOrPause: () -> Unit,
    onSeekToBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val music = musicDetailsScreenModel.music
    BackHandler(onBack = onBackClick)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    0.0f to AppPrimaryColor,
                    1.0f to AppSecondaryColor,
                    startY = 0.0f,
                    endY = 500f
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopDetailsMenu(
            music = music,
            onBackClick = onBackClick,
            playlistName = musicDetailsScreenModel.playlistName,
            modifier = modifier,
        )
        MusicPlayerScreen(
            musicPlayerScreenModel = MusicPlayerScreenModel(
                music = music,
                isPlaying = musicDetailsScreenModel.isPlaying
            ),
            onSeekBack = onSeekBack,
            onPlayOrPause = onPlayOrPause,
            onSeekToBack = onSeekToBack,
            modifier = modifier
        )
    }
}

@Composable
fun TopDetailsMenu(
    music: Music,
    playlistName: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dp16, vertical = dp24)
    ) {

        MusicButtons(imageIcon = R.drawable.arrow_left, onClick = onBackClick, modifier = modifier)

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextStyleTheme(
                modifier = modifier,
                text = stringResource(
                    id = R.string.tx_playing_from, fletchFromPlayingText(music.isFromPlaying)
                ),
                size = sp16,
                color = AuthorTextColor
            )
            TextStyleTheme(
                modifier = modifier,
                text = playlistName,
                size = sp18,
                color = MainTextColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        MusicButtons(imageIcon = R.drawable.menu, modifier = modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerScreen(
    musicPlayerScreenModel: MusicPlayerScreenModel,
    onSeekBack: () -> Unit,
    onPlayOrPause: () -> Unit,
    onSeekToBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val music = musicPlayerScreenModel.music
    Column(
        modifier = modifier.padding(horizontal = dp16),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.weight(1f))

        ImageStyleTheme(
            painter = painterResource(id = music.defaultIconId),
            onClick = {},
            modifier = modifier.size(dp180),
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

            Image(
                modifier = modifier
                    .size(dp24)
                    .clickableNoRipple(onClick = {
                        music.isFavorite = !music.isFavorite
                    }),
                painter = fetchFavoritePainter(music.isFavorite),
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
//            MusicButtons(
//                boxSize = dp42,
//                imageSize = dp18,
//                imageIcon = R.drawable.group,
//                modifier = modifier,
//            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                boxSize = dp52,
                imageSize = dp24,
                imageIcon = R.drawable.previous,
                onClick = onSeekBack,
                modifier = modifier,
            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                boxSize = dp62,
                imageSize = dp28,
                imageIcon = DEFAULT_INT,
                imageIconPainter = fetchIsPlayingPainter(isPlaying = musicPlayerScreenModel.isPlaying),
                onClick = onPlayOrPause,
                modifier = modifier,
            )

            Spacer(modifier = modifier.weight(1f))

            MusicButtons(
                boxSize = dp52,
                imageSize = dp24,
                imageIcon = R.drawable.next,
                onClick = onSeekToBack,
                modifier = modifier,
            )

            Spacer(modifier = modifier.weight(1f))

//            MusicButtons(
//                boxSize = dp42,
//                imageSize = dp18,
//                imageIcon = R.drawable.repeate_music,
//                modifier = modifier,
//            )
        }
    }
}

@Composable
private fun fletchFromPlayingText(isFromPlaylist: Boolean): String = stringResource(
    id = if (isFromPlaylist) R.string.tx_playlist else R.string.tx_main_list
)