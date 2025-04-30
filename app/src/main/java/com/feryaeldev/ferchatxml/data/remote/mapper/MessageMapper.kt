package com.feryaeldev.ferchatxml.data.remote.mapper

import com.feryaeldev.ferchatxml.data.remote.entity.response.MessageResponse
import com.feryaeldev.ferchatxml.domain.entity.Message
import com.feryaeldev.ferchatxml.domain.entity.User

fun MessageResponse.toBusiness(): Message {
    return Message(
        msg = msg.orEmpty(),
        hour = hour.orEmpty(),
        date = date.orEmpty(),
        user = user?.toBusiness() ?: User(userName = "", admin = false)
    )
}