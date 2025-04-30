package com.feryaeldev.ferchatxml.domain.usecase

import com.feryaeldev.ferchatxml.domain.repository.DatabaseRepository
import javax.inject.Inject

class SaveUsernameUseCase @Inject constructor(private val databaseRepository: DatabaseRepository) {
    suspend operator fun invoke(username: String) {
        databaseRepository.saveUsername(username)
    }
}