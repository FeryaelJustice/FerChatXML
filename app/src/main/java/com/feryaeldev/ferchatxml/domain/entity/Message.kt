package com.feryaeldev.ferchatxml.domain.entity

data class Message(
    val msg: String,
    val hour: String,
    val date: String,
    val user: User,
    var showDate: Boolean = false
)