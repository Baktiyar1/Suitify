package com.example.suitify.ui.screens.home.models

import javax.annotation.concurrent.Immutable

@Immutable
data class ScreenTopMenuModel(
    val isVisibleSearch: Boolean,
    val isVisibleCategory: Boolean,
)