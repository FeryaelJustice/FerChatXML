package com.feryaeldev.ferchatxml.data.repository

import com.feryaeldev.ferchatxml.data.local.service.DatastoreService
import com.feryaeldev.ferchatxml.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val datastoreService: DatastoreService
) :
    DatabaseRepository {
    override suspend fun saveUsername(username: String) = datastoreService.saveUsername(username)

    override fun getUsername(): Flow<String> = datastoreService.getUsername()

    override suspend fun closeSession() = datastoreService.closeSession()
}