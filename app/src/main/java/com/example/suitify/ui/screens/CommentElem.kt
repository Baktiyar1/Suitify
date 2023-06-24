package com.example.suitify.ui.screens

// Bottom Navigation View
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun CreateBottomNavigation(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    Scaffold(modifier = modifier.fillMaxWidth(), containerColor = AppPrimaryColor, bottomBar = {
//        BottomNavigationBar(items = listOf(
//            BottomNavItem(
//                route = "home",
//                name = "Home",
//                icon = ImageVector.vectorResource(id = R.drawable.home)
//            ),
//            BottomNavItem(
//                route = "search",
//                name = "Search",
//                icon = ImageVector.vectorResource(id = R.drawable.search)
//            ),
//            BottomNavItem(
//                route = "your_library",
//                name = "Your Library",
//                icon = ImageVector.vectorResource(id = R.drawable.layer)
//            ),
//        ), navController = navController, onItemClick = {
//            navController.navigate(it.route)
//        })
//    }) {
//        Navigation(navController = navController)
//    }
//}

// Items
//@Composable
//fun RecentlyPlayed(modifier: Modifier = Modifier, playlistList: List<Playlist>) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(color = AppPrimaryColor)
//            .padding(top = 24.dp)
//            .padding(8.dp),
//    ) {
//        Text(
//            text = "Recently played",
//            color = AppWhiteTextColor,
//            style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
//            fontSize = 16.sp,
//            modifier = modifier.padding(start = 16.dp)
//        )
//        LazyRow {
//            items(items = playlistList) { data ->
//                RecentlyPlayedItem(playlist = data)
//            }
//        }
//    }
//}
//
//@Composable
//fun RecentlyPlayedItem(modifier: Modifier = Modifier, playlist: Playlist) {
//    Box(
//        modifier = modifier
//            .padding(8.dp)
//            .shadow(
//                elevation = 16.dp,
//                shape = RoundedCornerShape(26.dp),
//                spotColor = BottomBarBackgroundColor
//            )
//            .background(
//                brush = Brush.verticalGradient(
//                    0.0f to MusicItemGradientFirstColor,
//                    1.0f to MusicItemGradientSecondColor,
//                    startY = 0.0f,
//                    endY = 250.0f
//                )
//            )
//    ) {
//        Box(
//            modifier = modifier
//                .padding(0.5.dp)
//                .shadow(elevation = 16.dp, shape = RoundedCornerShape(26.dp))
//                .background(color = MusicItemBackgroundColor)
//                .clickable(onClick = {})
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Image(
//                    modifier = modifier
//                        .padding(top = 14.dp, start = 17.dp, end = 17.dp)
//                        .size(76.dp)
//                        .clip(shape = RoundedCornerShape(26.dp)),
//                    painter = painterResource(id = R.drawable.author_default),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//
//                Text(
//                    text = playlist.name,
//                    modifier = modifier.padding(
//                        top = 12.dp, bottom = 14.dp, start = 19.5.dp, end = 19.5.dp
//                    ),
//                    style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
//                    fontSize = 11.sp,
//                    color = MusicItemTitleTextColor,
//                    maxLines = 1
//                )
//            }
//        }
//    }
//}

//@Composable
//fun ToGetYouStarted(modifier: Modifier = Modifier, mixList: List<Mix>) {
//    Column(
//        modifier = modifier
//            .fillMaxWidth()
//            .background(color = AppPrimaryColor)
//            .padding(top = 32.dp),
//    ) {
//        Text(
//            text = "To get you started",
//            color = AppWhiteTextColor,
//            style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
//            fontSize = 16.sp,
//            modifier = modifier.padding(start = 16.dp)
//        )
//        LazyRow {
//            items(items = mixList) { data ->
//                ToGetYouStartedItem(mix = data)
//            }
//        }
//    }
//}
//
//@Composable
//fun ToGetYouStartedItem(modifier: Modifier = Modifier, mix: Mix) {
//    Box(
//        modifier = modifier
//            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
//            .shadow(elevation = 16.dp, shape = RoundedCornerShape(26.dp))
//            .background(
//                brush = Brush.verticalGradient(
//                    0.0f to MusicItemGradientFirstColor,
//                    1.0f to MusicItemGradientSecondColor,
//                    startY = 0.0f,
//                    endY = 250.0f
//                )
//            )
//    ) {
//        Box(
//            modifier = modifier
//                .padding(0.5.dp)
//                .shadow(elevation = 16.dp, shape = RoundedCornerShape(26.dp))
//                .background(color = MusicItemBackgroundColor)
//                .clickable(onClick = {})
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Image(
//                    modifier = modifier
//                        .size(130.dp)
//                        .clip(shape = RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
//                    painter = painterResource(id = R.drawable.author_default),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//
//                Text(
//                    text = mix.name,
//                    modifier = modifier.padding(
//                        top = 12.dp, bottom = 14.dp, start = 19.5.dp, end = 19.5.dp
//                    ),
//                    style = TextStyle(fontStyle = FontStyle(R.font.urbanist_black)),
//                    fontSize = 11.sp,
//                    color = MusicItemTitleTextColor,
//                    maxLines = 1
//                )
//            }
//        }
//    }
//}