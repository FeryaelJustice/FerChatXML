package com.feryaeldev.ferchatxml.data.remote.entity.dto

import androidx.annotation.Keep

@Keep
data class UserDto(
    val userName: String, val admin: Boolean,
)
