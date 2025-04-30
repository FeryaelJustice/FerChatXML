package com.feryaeldev.ferchatxml.data.remote.entity.response

import androidx.annotation.Keep

@Keep
data class UserResponse(
    val userName: String? = null, val admin: Boolean? = null,
)
