package com.example.loginsocket.dto

data class Message(
    private val type: String,
    private val sender: String,
    private val channelId: String,
    private val data: Any
)
