package com.example.suitify.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.suitify.ui.screens.home.HomeScreen
import com.example.suitify.ui.screens.permissions.PermissionScreen
import com.example.suitify.ui.screens.permissions.PermissionViewModel
import com.example.suitify.ui.theme.SuitifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitifyTheme {
                val viewModel: PermissionViewModel = viewModel()
                PermissionScreen(viewModel = viewModel, activity = this@MainActivity)
                if (viewModel.isGrantedPermission.collectAsState().value) HomeScreen(viewModel())
            }
        }
    }
}