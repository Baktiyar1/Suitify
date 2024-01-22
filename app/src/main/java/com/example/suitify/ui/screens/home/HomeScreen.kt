package com.example.suitify.ui.screens.home

import android.util.Log
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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.DpSize
import com.example.suitify.R
import com.example.suitify.models.BottomSheet
import com.example.suitify.models.Music
import com.example.suitify.models.Playlist
import com.example.suitify.ui.extention.advancedShadow
import com.example.suitify.ui.extention.fadingEdge
import com.example.suitify.ui.screens.playlist.PlaylistScreen
import com.example.suitify.ui.theme.AppPrimaryColor
import com.example.suitify.ui.theme.AppSecondaryColor
import com.example.suitify.ui.theme.AuthorTextColor
import com.example.suitify.ui.theme.BottomSheetBackgroundColor
import com.example.suitify.ui.theme.ButtonColor
import com.example.suitify.ui.theme.ButtonShadowColor
import com.example.suitify.ui.theme.ImageStyleTheme
import com.example.suitify.ui.theme.MainTextColor
import com.example.suitify.ui.theme.MusicButtons
import com.example.suitify.ui.theme.MusicItemBackgroundColor
import com.example.suitify.ui.theme.MusicItemGradientFirstColor
import com.example.suitify.ui.theme.MusicItemGradientSecondColor
import com.example.suitify.ui.theme.PlaylistItemBackground
import com.example.suitify.ui.theme.ProgressColor
import com.example.suitify.ui.theme.ProgressTrackColor
import com.example.suitify.ui.theme.SearchColor
import com.example.suitify.ui.theme.TextStyleTheme
import com.example.suitify.ui.theme.dp05
import com.example.suitify.ui.theme.dp10
import com.example.suitify.ui.theme.dp12
import com.example.suitify.ui.theme.dp14
import com.example.suitify.ui.theme.dp16
import com.example.suitify.ui.theme.dp172
import com.example.suitify.ui.theme.dp2
import com.example.suitify.ui.theme.dp20
import com.example.suitify.ui.theme.dp24
import com.example.suitify.ui.theme.dp26
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    musics: List<Music>,
    playlists: List<Playlist>,
    music: Music,
    progress: Float,
    isVisibleSearch: Boolean,
    isVisibleCategory: Boolean,
    searchText: String,
    onProgress: (Float) -> Unit,
    onItemClick: (Int) -> Unit,
    onStart: () -> Unit,
    onNext: () -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSearchVisibilityChange: (Boolean) -> Unit,
    onCategoryVisibilityChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheetScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        scaffoldState = rememberBottomSheetScaffoldState(),
        sheetShape = RoundedCornerShape(topStart = dp26, topEnd = dp26),
        sheetPeekHeight = dp172,
        sheetContainerColor = BottomSheetBackgroundColor,
        sheetContent = {
            Column(
                modifier = modifier.padding(start = dp16, end = dp16, bottom = dp14),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomMenu(
                    music = music,
                    progress = progress,
                    onProgress = onProgress,
                    onStart = onStart,
                    onNext = onNext
                )

                Spacer(modifier = modifier.padding(top = dp16))

                BottomSheetItem(
                    modifier = modifier, bottomSheet = BottomSheet(
                        title = stringResource(id = R.string.tx_like),
                        iconId = R.drawable.not_favorite
                    )
                )

                BottomSheetItem(
                    modifier = modifier, bottomSheet = BottomSheet(
                        title = stringResource(id = R.string.tx_add_to_playlist),
                        iconId = R.drawable.playlist
                    )
                )

                BottomSheetItem(
                    modifier = modifier, bottomSheet = BottomSheet(
                        title = stringResource(id = R.string.tx_share), iconId = R.drawable.share
                    )
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
                TopMenu(
                    isVisibleSearch = isVisibleSearch,
                    isVisibleCategory = isVisibleCategory,
                    onCategoryVisibilityChange = onCategoryVisibilityChange,
                    onSearchVisibilityChange = onSearchVisibilityChange,
                    modifier = modifier,
                )
                SearchView(
                    musics = musics,
                    playlists = playlists,
                    isVisibleSearch = isVisibleSearch,
                    isVisibleCategory = isVisibleCategory,
                    searchText = searchText,
                    onItemClick = onItemClick,
                    onSearchTextChange = onSearchTextChange,
                    onSearchVisibilityChange = onSearchVisibilityChange,
                    modifier = modifier
                )
                Spacer(modifier = modifier.weight(1f))
            }
        }
    }
}

@Composable
fun TopMenu(
    isVisibleSearch: Boolean,
    isVisibleCategory: Boolean,
    onSearchVisibilityChange: (Boolean) -> Unit,
    onCategoryVisibilityChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(dp16)
    ) {
        Text(
            text = stringResource(id = R.string.tx_good_afternoon),
            color = MainTextColor,
            style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
            fontSize = sp18
        )

        Spacer(modifier = modifier.weight(5f))

        AnimatedVisibility(
            visible = !isVisibleSearch,
            enter = fadeIn(tween()) + expandHorizontally(animationSpec = tween()),
            exit = fadeOut(tween()) + shrinkVertically(animationSpec = tween())
        ) {
            MusicButtons(
                modifier = modifier
                    .padding(end = dp24)
                    .clickable { onSearchVisibilityChange(true) },
                boxSize = dp44,
                imageSize = dp20,
                imageIcon = R.drawable.search,
            )
        }

        MusicButtons(
            modifier = modifier.clickable { onCategoryVisibilityChange() },
            boxSize = dp44,
            imageSize = dp20,
            imageIcon = fetchCategoryPainter(isVisibleCategory),
        )
    }
}

@Composable
fun SearchView(
    musics: List<Music>,
    playlists: List<Playlist>,
    isVisibleSearch: Boolean,
    isVisibleCategory: Boolean,
    searchText: String,
    onItemClick: (Int) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSearchVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = isVisibleSearch) {
        Box(
            modifier = modifier
                .padding(top = dp8, start = dp16, end = dp16, bottom = dp24)
                .advancedShadow(color = ButtonShadowColor, shadowBlurRadius = dp32)
                .shadow(elevation = dp16, RoundedCornerShape(dp32))
                .background(color = Color.Transparent)
        ) {
            TextField(
                modifier = modifier
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
                value = searchText,
                onValueChange = { onSearchTextChange(it) },
                singleLine = true,
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = modifier
                            .clickable { onSearchVisibilityChange(false) }
                            .background(color = Color.Transparent))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.tx_what_do_you_want_to_listen_to),
                        fontSize = sp16,
                        color = AuthorTextColor,
                        modifier = modifier.background(color = Color.Transparent),
                        fontStyle = FontStyle(value = R.font.urbanist_black)
                    )
                })
        }
    }

    AnimatedVisibility(visible = isVisibleCategory) {
        PlaylistScreen(modifier = modifier, playlistList = playlists)
    }
    AnimatedVisibility(visible = !isVisibleCategory) {
        MusicItemList(modifier = modifier, onItemClick = onItemClick, musics = musics)
    }
}

@Composable
fun MusicItemList(musics: List<Music>, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .fadingEdge(
                Brush.verticalGradient(
                    0f to Color.Transparent,
                    0.05f to Color.Red,
                    0.95f to Color.Red,
                    1f to Color.Transparent
                )
            )
    ) {
        itemsIndexed(items = musics) { index, music ->
            MusicItem(
                modifier = modifier,
                onItemClick = { onItemClick(index) },
                music = music
            )
        }
    }
}

@Composable
fun MusicItem(music: Music, onItemClick: () -> Unit, modifier: Modifier = Modifier) {
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
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) { onItemClick() }) {
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
                    painter = painterResource(id = music.defaultIconId),
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
                    painter = fetchIsPlayingPainter(isFirstImage = music.isPlaying),
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
                    text = music.artist,
                    size = sp12,
                    color = AuthorTextColor
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomMenu(
    music: Music,
    progress: Float,
    onProgress: (Float) -> Unit,
    onStart: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp26))
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

                MusicPlayInfo(modifier = modifier, music = music)

                Spacer(modifier = Modifier.weight(1f))

                val isPlayingImage = remember { mutableStateOf(music.isPlaying) }

                ImageStyleTheme(
                    modifier = modifier
                        .padding(end = dp16)
                        .size(dp20)
                        .clickable {
                            onStart()
                            isPlayingImage.value = !isPlayingImage.value
                        },
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

                ImageStyleTheme(
                    modifier = modifier
                        .size(dp20)
                        .clickable { onNext() },
                    painter = painterResource(id = R.drawable.next)
                )
            }

            val interactionSource = remember { MutableInteractionSource() }

            Slider(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp12),
                value = progress,
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
        modifier = modifier
            .size(dp42)
            .shadow(elevation = dp16, shape = RoundedCornerShape(dp32)),
        painter = painterResource(id = music.defaultIconId)
    )

    Column(modifier = modifier.padding(start = dp8)) {
        TextStyleTheme(
            modifier = modifier, text = music.title, size = sp12, color = MainTextColor
        )

        TextStyleTheme(
            modifier = modifier.padding(top = dp4),
            text = music.artist,
            size = sp12,
            color = AuthorTextColor
        )
    }
}

@Composable
fun BottomSheetItem(bottomSheet: BottomSheet, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(bottom = dp16)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
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
    painterResource(id = if (isFirstImage) R.drawable.favorite else R.drawable.not_favorite)

@Composable
private fun fetchIsPlayingPainter(isFirstImage: Boolean): Painter =
    painterResource(id = if (isFirstImage) R.drawable.pause else R.drawable.play)

@Composable
private fun fetchVolumePainter(isFirstImage: Boolean): Painter =
    painterResource(id = if (isFirstImage) R.drawable.volume_off else R.drawable.volume)

@Composable
private fun fetchCategoryPainter(isFirstImage: Boolean): Int =
    if (isFirstImage) R.drawable.music else R.drawable.playlist