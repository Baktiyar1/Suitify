package com.example.suitify.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.utils.LAUNCHED_EFFECT_AUDIO_PERMISSION_KEY
import com.example.suitify.ui.dialog.AudioPermissionTextProvides
import com.example.suitify.ui.dialog.PermissionDialog
import com.example.suitify.ui.extention.openAppSettings
import com.example.suitify.ui.screens.home.HomeScreen
import com.example.suitify.ui.theme.AppPrimaryColor
import com.example.suitify.ui.theme.SuitifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitifyTheme {
                val viewModel: PermissionViewModel = viewModel()
                PermissionScreen(viewModel = viewModel)
                if (viewModel.isGrantedPermission.collectAsState().value) HomeScreen(viewModel())
            }
        }
    }

    @Composable
    fun PermissionScreen(viewModel: PermissionViewModel) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppPrimaryColor),
            contentAlignment = Alignment.Center
        ) {
            val dialogQueue = viewModel.visiblePermissionDialogQueue

            val audioPermissionResultLauncher =
                rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        viewModel.onPermissionResult(getAudioPermission(), isGranted)
                    })
            LaunchedEffect(key1 = LAUNCHED_EFFECT_AUDIO_PERMISSION_KEY, block = {
                audioPermissionResultLauncher.launch(getAudioPermission())
            })
            dialogQueue.reversed().forEach { permission ->
                PermissionDialog(context = this@MainActivity,
                    permissionTextProvides = AudioPermissionTextProvides(),
                    isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                        this@MainActivity, permission
                    ),
                    onDismiss = viewModel::dismissDialog,
                    onOkClick = {
                        viewModel.dismissDialog()
                        audioPermissionResultLauncher.launch(getAudioPermission())
                    },
                    onGoToAppSettingsClick = {
                        openAppSettings()
                    })
            }
//            if (viewModel.isGrantedPermission.collectAsState().value) {
//                HomeScreen(viewModel = viewModel())
//            }
        }
    }

    private fun getAudioPermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_AUDIO
        else Manifest.permission.READ_EXTERNAL_STORAGE

}