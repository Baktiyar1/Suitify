package com.example.suitify.ui.navigation.bottom_navigation

//@Composable
//fun BottomNavigationBar(
//    items: List<BottomNavItem>,
//    navController: NavController,
//    modifier: Modifier = Modifier,
//    onItemClick: (BottomNavItem) -> Unit
//) {
//    NavigationBar(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(start = 19.dp, end = 19.dp, bottom = 18.5.dp)
//            .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp))
//            .height(height = 54.dp),
//        containerColor = BottomBarColor
//    ) {
//        items.forEach { item ->
//            val selected =
//                item.route == navController.currentBackStackEntryAsState().value?.destination?.route
//            NavigationBarItem(
//                selected = selected,
//                onClick = { onItemClick(item) },
//                colors = NavigationBarItemDefaults.colors(
//                    unselectedIconColor = BottomBarTextAndIconUnselectedColor,
//                    unselectedTextColor = BottomBarTextAndIconUnselectedColor,
//                    selectedIconColor = BottomBarTextAndIconSelectedColor,
//                    selectedTextColor = BottomBarTextAndIconSelectedColor,
//                    indicatorColor = BottomBarColor
//                ),
//                icon = {
//                    Column(horizontalAlignment = CenterHorizontally) {
//                        Icon(imageVector = item.icon, contentDescription = item.name)
//                        if (selected) {
//                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
//                        }
//                    }
//                },
//            )
//        }
//    }
//}