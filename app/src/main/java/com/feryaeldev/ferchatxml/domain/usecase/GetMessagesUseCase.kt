package com.feryaeldev.ferchatxml.domain.usecase

import com.feryaeldev.ferchatxml.domain.repository.RemoteDatabaseRepository
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(private val remoteDatabaseRepository: RemoteDatabaseRepository){
    operator fun invoke() = remoteDatabaseRepository.getMessages()
}