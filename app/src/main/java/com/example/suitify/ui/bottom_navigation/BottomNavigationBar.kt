package com.example.suitify.ui.bottom_navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.suitify.ui.theme.BottomBarColor
import com.example.suitify.ui.theme.BottomBarTextAndIconSelectedColor
import com.example.suitify.ui.theme.BottomBarTextAndIconUnselectedColor

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 19.dp, end = 19.dp, bottom = 18.5.dp)
            .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp))
            .height(height = 54.dp),
        containerColor = BottomBarColor
    ) {
        items.forEach { item ->
            val selected =
                item.route == navController.currentBackStackEntryAsState().value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = BottomBarTextAndIconUnselectedColor,
                    unselectedTextColor = BottomBarTextAndIconUnselectedColor,
                    selectedIconColor = BottomBarTextAndIconSelectedColor,
                    selectedTextColor = BottomBarTextAndIconSelectedColor,
                    indicatorColor = BottomBarColor
                ),
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                },
            )
        }
    }
}