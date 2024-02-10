package com.example.suitify.ui.screens.permissions

import android.Manifest
import android.app.Activity
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.core.LAUNCHED_EFFECT_AUDIO_PERMISSION_KEY
import com.example.core_ui.extention.openAppSettings
import com.example.core_ui.theme.AppPrimaryColor
import com.example.suitify.ui.dialog.AudioPermissionTextProvides
import com.example.suitify.ui.dialog.PermissionDialog

@Composable
fun PermissionScreen(viewModel: PermissionViewModel, activity: Activity) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor),
        contentAlignment = Alignment.Center
    ) {

        val audioPermissionResultLauncher =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted ->
                    viewModel.onPermissionResult(getAudioPermission(), isGranted)
                })
        LaunchedEffect(key1 = LAUNCHED_EFFECT_AUDIO_PERMISSION_KEY, block = {
            audioPermissionResultLauncher.launch(getAudioPermission())
        })
        viewModel.visiblePermissionDialogQueue.reversed().forEach { permission ->
            PermissionDialog(
                context = activity,
                permissionTextProvides = AudioPermissionTextProvides(),
                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity, permission
                ),
                onDismiss = viewModel::dismissDialog,
                onOkClick = {
                    viewModel.dismissDialog()
                    audioPermissionResultLauncher.launch(getAudioPermission())
                },
                onGoToAppSettingsClick = activity::openAppSettings
            )
        }
    }
}

private fun getAudioPermission() =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_AUDIO
    else Manifest.permission.READ_EXTERNAL_STORAGE