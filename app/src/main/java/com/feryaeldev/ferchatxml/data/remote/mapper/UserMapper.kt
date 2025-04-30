package com.feryaeldev.ferchatxml.data.remote.mapper

import com.feryaeldev.ferchatxml.data.remote.entity.response.UserResponse
import com.feryaeldev.ferchatxml.domain.entity.User

fun UserResponse.toBusiness(): User {
    return User(
        userName = userName.orEmpty(),
        admin = admin == true
    )
}