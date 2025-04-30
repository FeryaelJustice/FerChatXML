package com.feryaeldev.ferchatxml.domain.repository

import com.feryaeldev.ferchatxml.data.remote.entity.dto.MessageDto
import com.feryaeldev.ferchatxml.domain.entity.Message
import kotlinx.coroutines.flow.Flow

interface RemoteDatabaseRepository {
    fun sendMsg(message: MessageDto)
    fun getMessages(): Flow<List<Message>>
}