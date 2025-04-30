package com.feryaeldev.ferchatxml.data.repository

import com.feryaeldev.ferchatxml.data.remote.entity.dto.MessageDto
import com.feryaeldev.ferchatxml.data.remote.service.FirebaseDatabaseService
import com.feryaeldev.ferchatxml.domain.entity.Message
import com.feryaeldev.ferchatxml.domain.repository.RemoteDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDatabaseRepositoryImpl @Inject constructor(private val firebaseDatabaseService: FirebaseDatabaseService) :
    RemoteDatabaseRepository {
    override fun sendMsg(message: MessageDto) = firebaseDatabaseService.sendMsg(message)

    override fun getMessages(): Flow<List<Message>> = firebaseDatabaseService.getMessages()
}