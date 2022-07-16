package com.example.loginsocket.dto

data class LoginRequest(
    val id: String,
    val password: String,
    var message: String
)
