package com.example.suitify.ui.screens.main_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.theme.SuitifyTheme
import com.example.player.service.MusicService
import com.example.suitify.ui.screens.home.HomeViewModel
import com.example.suitify.ui.screens.main_activity.bottom_sheet_scaffold.BottomSheetScaffoldScreen
import com.example.suitify.ui.screens.main_activity.permissions.PermissionScreen
import com.example.suitify.ui.screens.main_activity.permissions.PermissionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitifyTheme {
                val permissionViewModel: PermissionViewModel = hiltViewModel()
                PermissionScreen(viewModel = permissionViewModel, activity = this@MainActivity)
                val homeViewModel: HomeViewModel = hiltViewModel()
                if (permissionViewModel.isGrantedPermission.collectAsState().value) BottomSheetScaffoldScreen()
                startService(isServiceRunning = homeViewModel.isServiceRunning.collectAsState().value)
            }
        }
    }

    private fun startService(isServiceRunning: Boolean) {
        if (!isServiceRunning) startForegroundService(Intent(this, MusicService::class.java))
    }
}