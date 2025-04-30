package com.feryaeldev.ferchatxml.domain.usecase

import com.feryaeldev.ferchatxml.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUsernameUseCase  @Inject constructor(private val databaseRepository: DatabaseRepository) {
    suspend operator fun invoke() = databaseRepository.getUsername().first()
}