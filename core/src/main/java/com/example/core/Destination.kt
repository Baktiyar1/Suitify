package com.example.core

interface Destination {
    fun route(): String
    fun routeWithArgs(): String
}