package com.feryaeldev.ferchatxml.domain.usecase

import com.feryaeldev.ferchatxml.domain.repository.DatabaseRepository
import javax.inject.Inject

class CloseSessionUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {
    suspend operator fun invoke() = databaseRepository.closeSession()
}