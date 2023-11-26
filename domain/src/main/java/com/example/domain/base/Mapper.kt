package com.example.domain.base

interface Mapper<From, To> {
    fun map(from: From): To
}