package com.example.core.managers

import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    fun destinationFlow(): Flow<Pair<String, Boolean>>

    fun navigateTo(destination: String, isClearBackStack: Boolean = false)
}