package com.feryaeldev.ferchatxml.data.remote.entity.dto

import androidx.annotation.Keep

@Keep
data class MessageDto(
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserDto
)
