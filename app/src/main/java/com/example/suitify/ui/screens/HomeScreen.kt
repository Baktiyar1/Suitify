package com.example.suitify.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.suitify.R
import com.example.suitify.models.BottomSheet
import com.example.suitify.models.Music
import com.example.suitify.ui.extention.advancedShadow
import com.example.suitify.ui.extention.fadingEdge
import com.example.suitify.ui.theme.AppPrimaryColor
import com.example.suitify.ui.theme.AppSecondaryColor
import com.example.suitify.ui.theme.AuthorTextColor
import com.example.suitify.ui.theme.MusicButtons
import com.example.suitify.ui.theme.BottomSheetBackgroundColor
import com.example.suitify.ui.theme.ButtonColor
import com.example.suitify.ui.theme.ButtonShadowColor
import com.example.suitify.ui.theme.ImageStyleTheme
import com.example.suitify.ui.theme.MainTextColor
import com.example.suitify.ui.theme.MusicItemBackgroundColor
import com.example.suitify.ui.theme.MusicItemGradientFirstColor
import com.example.suitify.ui.theme.MusicItemGradientSecondColor
import com.example.suitify.ui.theme.PlaylistItemBackground
import com.example.suitify.ui.theme.ProgressColor
import com.example.suitify.ui.theme.ProgressTrackColor
import com.example.suitify.ui.theme.SearchColor
import com.example.suitify.ui.theme.TextStyleTheme
import com.example.suitify.ui.theme.dp0
import com.example.suitify.ui.theme.dp05
import com.example.suitify.ui.theme.dp10
import com.example.suitify.ui.theme.dp12
import com.example.suitify.ui.theme.dp14
import com.example.suitify.ui.theme.dp16
import com.example.suitify.ui.theme.dp2
import com.example.suitify.ui.theme.dp20
import com.example.suitify.ui.theme.dp24
import com.example.suitify.ui.theme.dp26
import com.example.suitify.ui.theme.dp30
import com.example.suitify.ui.theme.dp32
import com.example.suitify.ui.theme.dp4
import com.example.suitify.ui.theme.dp42
import com.example.suitify.ui.theme.dp44
import com.example.suitify.ui.theme.dp8
import com.example.suitify.ui.theme.dp90
import com.example.suitify.ui.theme.sp12
import com.example.suitify.ui.theme.sp14
import com.example.suitify.ui.theme.sp16
import com.example.suitify.ui.theme.sp18
import com.example.suitify.ui.viewModels.HomeViewModel

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val viewModel = viewModel<HomeViewModel>()

    val music = viewModel.music.collectAsState().value

    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = dp26, topEnd = dp26),
        sheetPeekHeight = dp0,
        sheetContainerColor = BottomSheetBackgroundColor,
        sheetContent = {
            Column(
                modifier = modifier.padding(start = dp16, end = dp16, bottom = dp14),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = dp16),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    MusicPlayInfo(modifier = modifier, music = music)

                    Spacer(modifier = Modifier.weight(1f))

                    MusicButtons(
                        modifier = modifier
                            .padding(end = dp16)
                            .clickable { viewModel.onSearchVisibilityChange() },
                        boxSize = dp44,
                        imageSize = dp20,
                        imageIcon = R.drawable.arrow_down,
                    )
                }

                Column(modifier = modifier.padding(top = dp30)) {
                    BottomSheetItem(
                        modifier = modifier,
                        bottomSheet = BottomSheet(title = "Like", iconId = R.drawable.not_favorite)
                    )
                }

                BottomSheetItem(
                    modifier = modifier,
                    BottomSheet(title = "Add to playlist", iconId = R.drawable.playlist)
                )

                BottomSheetItem(
                    modifier = modifier,
                    bottomSheet = BottomSheet(title = "Share", iconId = R.drawable.share)
                )
            }
        },
    ) {
        Column(
            modifier = modifier.background(
                brush = Brush.verticalGradient(
                    0.0f to AppPrimaryColor, 1.0f to AppSecondaryColor, startY = 0.0f, endY = 500f
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(12f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopMenu(modifier = modifier, viewModel = viewModel)
                SearchView(modifier = modifier, viewModel = viewModel)
                Spacer(modifier = modifier.weight(1f))
            }
            Box(modifier = modifier.weight(2f)) {
                BottomMenu(modifier = modifier, viewModel = viewModel)
            }
        }
    }
}

@Composable
fun TopMenu(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dp16)
    ) {
        Text(
            text = "Good afternoon",
            color = MainTextColor,
            style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
            fontSize = sp18
        )

        Spacer(modifier = modifier.weight(5f))

        AnimatedVisibility(
            visible = !viewModel.isVisibleSearch.collectAsState().value,
            enter = fadeIn(tween()) + expandHorizontally(animationSpec = tween()),
            exit = fadeOut(tween()) + shrinkVertically(animationSpec = tween())
        ) {
            MusicButtons(
                modifier = modifier
                    .padding(end = dp24)
                    .clickable { viewModel.onSearchVisibilityChange() },
                boxSize = dp44,
                imageSize = dp20,
                imageIcon = R.drawable.search,
            )
        }

        MusicButtons(
            modifier = modifier.clickable { viewModel.onCategoryVisibilityChange() },
            boxSize = dp44,
            imageSize = dp20,
            imageIcon = fetchCategoryPainter(viewModel.isVisibleCategory.collectAsState().value),
        )
    }
}

@Composable
fun SearchView(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val searchText = viewModel.searchText.collectAsState()
    val isVisible = viewModel.isVisibleSearch.collectAsState()
    val musics = viewModel.musics.collectAsState()
    val playlists = viewModel.playlists.collectAsState()
    AnimatedVisibility(visible = isVisible.value) {
        Box(
            modifier = modifier
                .padding(top = dp8, start = dp16, end = dp16, bottom = dp24)
                .advancedShadow(color = ButtonShadowColor, shadowBlurRadius = dp32)
                .shadow(elevation = dp16, RoundedCornerShape(dp32))
                .background(color = Color.Transparent)
        ) {
            TextField(modifier = modifier
                .fillMaxWidth()
                .padding(dp2)
                .shadow(elevation = dp16, RoundedCornerShape(dp32))
                .background(color = Color.Transparent),
                colors = TextFieldDefaults.colors(
                    unfocusedTextColor = AuthorTextColor,
                    disabledTextColor = AuthorTextColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = MainTextColor,
                    disabledIndicatorColor = SearchColor,
                    unfocusedLabelColor = SearchColor,
                ),
                value = searchText.value,
                onValueChange = viewModel::onSearchTextChange,
                singleLine = true,
                leadingIcon = {
                    Image(painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = modifier
                            .clickable { viewModel.onSearchVisibilityChange() }
                            .background(color = Color.Transparent))
                },
                placeholder = {
                    Text(
                        text = "What do you want to listen to?",
                        fontSize = sp16,
                        color = AuthorTextColor,
                        modifier = modifier.background(color = Color.Transparent),
                        fontStyle = FontStyle(value = R.font.urbanist_black)
                    )
                })
        }
    }

    viewModel.testNewMusics()
    viewModel.testNewPlaylists()
    AnimatedVisibility(visible = viewModel.isVisibleCategory.collectAsState().value) {
        PlaylistScreen(modifier = modifier, playlistList = playlists.value)
    }
    AnimatedVisibility(visible = !viewModel.isVisibleCategory.collectAsState().value) {
        MusicItemList(modifier = modifier, musicList = musics.value, viewModel = viewModel)
    }
}

@Composable
fun MusicItemList(modifier: Modifier = Modifier, musicList: List<Music>, viewModel: HomeViewModel) {
    val topBottomFade = Brush.verticalGradient(
        0f to Color.Transparent, 0.05f to Color.Red, 0.95f to Color.Red, 1f to Color.Transparent
    )
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fadingEdge(topBottomFade)
    ) {
        items(items = musicList) { music ->
            MusicItem(
                modifier = modifier, music = music, viewModel = viewModel
            )
        }
    }
}

@Composable
fun MusicItem(modifier: Modifier = Modifier, music: Music, viewModel: HomeViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPlaying = viewModel.isPlaying.collectAsState()
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = dp16, vertical = dp10)
        .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
        .background(
            brush = Brush.verticalGradient(
                0.0f to MusicItemGradientFirstColor,
                1.0f to MusicItemGradientSecondColor,
                startY = 0.0f,
                endY = 250.0f
            )
        )
        .clickable(interactionSource = interactionSource, indication = null) {}) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(dp05)
                .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
                .background(color = MusicItemBackgroundColor)
                .clickable(onClick = {})
        ) {
            Box(modifier = modifier.padding(start = dp20, top = dp20, bottom = dp20)) {
                Image(
                    painter = painterResource(id = music.iconId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(dp90)
                        .shadow(
                            elevation = dp16,
                            shape = RoundedCornerShape(dp26),
                            spotColor = PlaylistItemBackground
                        )
                )

                Image(
                    painter = if (isPlaying.value) painterResource(R.drawable.pause)
                    else painterResource(R.drawable.play),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = modifier.align(alignment = Alignment.Center)
                )
            }

            Column(
                modifier = modifier.padding(start = dp12, top = dp20, bottom = dp20, end = dp20)
            ) {
                Row(modifier = modifier) {
                    Spacer(modifier = modifier.weight(5f))

                    val isFavoriteImage = remember { mutableStateOf(music.isFavorite) }

                    Image(
                        modifier = modifier
                            .padding(top = dp2, end = dp16)
                            .shadow(elevation = dp8, shape = RoundedCornerShape(dp16))
                            .clickable(onClick = {
                                music.isFavorite = !music.isFavorite
                                isFavoriteImage.value = music.isFavorite
                            }),
                        painter = fetchFavoritePainter(isFavoriteImage.value),
                        contentDescription = null,
                    )

                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = null,
                        modifier = modifier.padding(top = dp2)
                    )
                }

                TextStyleTheme(
                    modifier = modifier.padding(top = dp8),
                    text = music.title,
                    size = sp16,
                    color = MainTextColor
                )

                TextStyleTheme(
                    modifier = modifier.padding(top = dp4),
                    text = music.executor,
                    size = sp12,
                    color = AuthorTextColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMenu(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val music = viewModel.music.collectAsState().value
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = dp16, shape = RoundedCornerShape(topEnd = dp26, topStart = dp26))
            .background(color = AppPrimaryColor)
    ) {
        Column(
            modifier = modifier
                .padding(top = dp16, start = dp16, end = dp16)
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                MusicPlayInfo(modifier, music)

                Spacer(modifier = Modifier.weight(1f))

                val isPlayingImage = remember { mutableStateOf(viewModel.isPlaying.value) }

                ImageStyleTheme(
                    modifier = modifier
                        .padding(end = dp16)
                        .size(dp20)
                        .clickable { isPlayingImage.value = !isPlayingImage.value },
                    painter = fetchIsPlayingPainter(isFirstImage = isPlayingImage.value)
                )

                val isFavoriteImage = remember { mutableStateOf(music.isFavorite) }

                ImageStyleTheme(
                    modifier = modifier
                        .padding(end = dp16)
                        .size(dp20)
                        .clickable {
                            music.isFavorite = !music.isFavorite
                            isFavoriteImage.value = music.isFavorite
                        }, painter = fetchFavoritePainter(isFirstImage = isFavoriteImage.value)
                )

                val isVolumeImage = remember { mutableStateOf(viewModel.isVolume.value) }

                ImageStyleTheme(
                    modifier = modifier
                        .size(dp20)
                        .clickable { isVolumeImage.value = !isVolumeImage.value },
                    painter = fetchVolumePainter(isFirstImage = isVolumeImage.value)
                )
            }

            val sliderPosition = remember { mutableStateOf(value = 0.05f) }
            val interactionSource = remember { MutableInteractionSource() }

            Slider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp12),
                value = sliderPosition.value,
                onValueChange = { sliderPosition.value = it },
                valueRange = 0f..1f,
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
private fun MusicPlayInfo(modifier: Modifier, music: Music) {
    ImageStyleTheme(
        modifier = modifier
            .size(dp42)
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp32)),
        painter = painterResource(id = music.iconId)
    )

    Column(modifier = modifier.padding(start = dp8)) {
        TextStyleTheme(
            modifier = modifier, text = music.title, size = sp12, color = MainTextColor
        )

        TextStyleTheme(
            modifier = modifier.padding(top = dp4),
            text = music.executor,
            size = sp12,
            color = AuthorTextColor
        )
    }
}

@Composable
fun BottomSheetItem(modifier: Modifier = Modifier, bottomSheet: BottomSheet) {
    Row(
        modifier = modifier.padding(bottom = dp16), verticalAlignment = Alignment.CenterVertically
    ) {
        MusicButtons(
            modifier = modifier,
            boxSize = dp44,
            imageSize = dp20,
            imageIcon = bottomSheet.iconId,
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(dp44)
                .padding(start = dp12)
                .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
                .background(ButtonColor),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextStyleTheme(
                modifier = modifier.padding(start = dp14),
                text = bottomSheet.title,
                size = sp14,
                color = MainTextColor
            )
        }
    }
}

@Composable
private fun fetchFavoritePainter(isFirstImage: Boolean): Painter =
    if (isFirstImage) painterResource(id = R.drawable.favorite)
    else painterResource(R.drawable.not_favorite)

@Composable
private fun fetchIsPlayingPainter(isFirstImage: Boolean): Painter =
    if (isFirstImage) painterResource(id = R.drawable.play)
    else painterResource(R.drawable.pause)


@Composable
private fun fetchVolumePainter(isFirstImage: Boolean): Painter =
    if (isFirstImage) painterResource(id = R.drawable.volume_off)
    else painterResource(R.drawable.volume)

@Composable
private fun fetchCategoryPainter(isFirstImage: Boolean): Int = if (isFirstImage) R.drawable.music
else R.drawable.playlist