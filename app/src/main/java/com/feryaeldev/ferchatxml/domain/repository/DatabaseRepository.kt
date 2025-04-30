package com.feryaeldev.ferchatxml.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatabaseRepository{
    suspend fun saveUsername(username: String)
    fun getUsername(): Flow<String>
    suspend fun closeSession()
}