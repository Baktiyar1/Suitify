package com.example.suitify.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.suitify.ui.screens.HomeScreen
import com.example.suitify.ui.screens.MusicDetailsScreen
import com.example.suitify.ui.theme.SuitifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuitifyTheme {
                HomeScreen()
            }
        }
    }
}