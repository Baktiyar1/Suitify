package com.example.suitify.ui.screens.main_activity.bottom_sheet_scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.core.EMPTY_STRING
import com.example.core_ui.R
import com.example.core_ui.theme.AppPrimaryColor
import com.example.core_ui.theme.AuthorTextColor
import com.example.core_ui.theme.BottomSheetBackgroundColor
import com.example.core_ui.theme.ImageStyleTheme
import com.example.core_ui.theme.MainTextColor
import com.example.core_ui.theme.ProgressColor
import com.example.core_ui.theme.ProgressTrackColor
import com.example.core_ui.theme.TextStyleTheme
import com.example.core_ui.theme.dp12
import com.example.core_ui.theme.dp14
import com.example.core_ui.theme.dp16
import com.example.core_ui.theme.dp172
import com.example.core_ui.theme.dp180
import com.example.core_ui.theme.dp20
import com.example.core_ui.theme.dp26
import com.example.core_ui.theme.dp4
import com.example.core_ui.theme.dp42
import com.example.core_ui.theme.dp8
import com.example.core_ui.theme.sp12
import com.example.suitify.models.BottomSheetModel
import com.example.suitify.models.Music
import com.example.suitify.ui.navigation.app_navigation.AppNavGraph
import com.example.suitify.ui.screens.home.BottomSheetItem
import com.example.suitify.ui.screens.home.HomeViewModel
import com.example.suitify.ui.screens.home.models.ScreenBottomMenuModel
import com.example.suitify.ui.screens.home.models.UiEvents
import com.example.suitify.ui.screens.icons.fetchFavoritePainter
import com.example.suitify.ui.screens.icons.fetchIsPlayingPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldScreen(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    with(homeViewModel) {
        val isBottomSheetVisibility by this.isBottomSheetVisibility.collectAsState()
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = SheetState(
                skipPartiallyExpanded = false,
                initialValue = if (isBottomSheetVisibility) SheetValue.PartiallyExpanded else SheetValue.Hidden,
                confirmValueChange = {
                    if (isBottomSheetVisibility) it != SheetValue.Hidden else it == SheetValue.Hidden
                })
        )
        BottomSheetScaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(topStart = dp26, topEnd = dp26),
            sheetPeekHeight = dp172,
            sheetContainerColor = BottomSheetBackgroundColor,
            sheetContent = {
                Column(
                    modifier = modifier.padding(
                        start = dp16,
                        end = dp16,
                        bottom = dp14
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BottomMenu(
                        screenBottomMenuModel = ScreenBottomMenuModel(
                            music = playingMusic.collectAsState().value,
                            progress = progress,
                            isPlaying = isPlaying.collectAsState().value
                        ),
                        onNavigateToDetails = { onUiEvents(UiEvents.NavigateToDetails) },
                        onProgress = { onUiEvents(UiEvents.UpdateProgress(it)) },
                        onStart = { onUiEvents(UiEvents.PlayPause) },
                        onNext = { onUiEvents(UiEvents.SeekToNext) }
                    )

                    Spacer(modifier = modifier.padding(top = dp16))

                    BottomSheetItem(
                        modifier = modifier, bottomSheetModel = BottomSheetModel(
                            title = stringResource(id = R.string.tx_like),
                            iconId = R.drawable.not_favorite
                        )
                    )

                    BottomSheetItem(
                        modifier = modifier, bottomSheetModel = BottomSheetModel(
                            title = stringResource(id = R.string.tx_add_to_playlist),
                            iconId = R.drawable.playlist
                        )
                    )

                    BottomSheetItem(
                        modifier = modifier, bottomSheetModel = BottomSheetModel(
                            title = stringResource(id = R.string.tx_share),
                            iconId = R.drawable.share
                        )
                    )
                }
            },
        ) {
            val navHostController = rememberNavController()

            val (destination, isClearBackStack) = destinationFlow.collectAsState(initial = EMPTY_STRING to false).value

            if (destination.isNotEmpty()) navHostController.navigate(destination) {
                if (isClearBackStack) popUpTo(id = 0)
            }
            AppNavGraph(navHostController = navHostController, homeViewModel = homeViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMenu(
    screenBottomMenuModel: ScreenBottomMenuModel,
    onNavigateToDetails: () -> Unit,
    onProgress: (Float) -> Unit,
    onStart: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val music = screenBottomMenuModel.music
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
            .background(color = AppPrimaryColor)
            .clickable { onNavigateToDetails() }
    ) {
        Column(
            modifier = modifier
                .padding(top = dp16, start = dp16, end = dp16)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                MusicPlayInfo(modifier = modifier, music = music)

                Spacer(modifier = Modifier.weight(1f))

                ImageStyleTheme(
                    painter = fetchIsPlayingPainter(isPlaying = screenBottomMenuModel.isPlaying),
                    onClick = onStart,
                    modifier = modifier
                        .padding(end = dp16)
                        .size(dp20),
                )

                ImageStyleTheme(
                    painter = fetchFavoritePainter(isFavorite = music.isFavorite),
                    onClick = { music.isFavorite = !music.isFavorite },
                    modifier = modifier
                        .padding(end = dp16)
                        .size(dp20)
                )

                ImageStyleTheme(
                    painter = painterResource(id = R.drawable.next),
                    onClick = onNext,
                    modifier = modifier.size(dp20)
                )
            }

            val interactionSource = remember { MutableInteractionSource() }

            Slider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp12),
                value = screenBottomMenuModel.progress,
                onValueChange = { onProgress(it) },
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
                    SliderDefaults.Thumb(
                        interactionSource = interactionSource,
                        thumbSize = DpSize(dp8, dp4),
                        colors = SliderDefaults.colors(thumbColor = ProgressColor)
                    )
                },
            )
        }
    }
}

@Composable
private fun MusicPlayInfo(music: Music, modifier: Modifier = Modifier) {
    ImageStyleTheme(
        painter = painterResource(id = music.defaultIconId),
        onClick = {},
        modifier = modifier.size(dp42),
    )

    Column(
        modifier = modifier
            .padding(start = dp8)
            .width(dp180)
    ) {
        TextStyleTheme(
            text = music.title,
            size = sp12,
            color = MainTextColor,
            modifier = modifier.horizontalScroll(state = rememberScrollState()),
        )

        TextStyleTheme(
            text = music.artist,
            size = sp12,
            color = AuthorTextColor,
            modifier = modifier.padding(top = dp4),
        )
    }
}