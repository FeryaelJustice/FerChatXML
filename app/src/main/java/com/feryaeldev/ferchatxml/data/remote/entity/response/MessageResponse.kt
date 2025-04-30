package com.feryaeldev.ferchatxml.data.remote.entity.response

import androidx.annotation.Keep

@Keep
data class MessageResponse(
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
)