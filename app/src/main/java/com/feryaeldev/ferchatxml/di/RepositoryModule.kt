package com.feryaeldev.ferchatxml.di

import com.feryaeldev.ferchatxml.data.repository.DatabaseRepositoryImpl
import com.feryaeldev.ferchatxml.data.repository.RemoteDatabaseRepositoryImpl
import com.feryaeldev.ferchatxml.domain.repository.DatabaseRepository
import com.feryaeldev.ferchatxml.domain.repository.RemoteDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDatabaseRepository(impl: DatabaseRepositoryImpl): DatabaseRepository

    @Binds
    abstract fun bindRemoteDatabaseRepository(impl: RemoteDatabaseRepositoryImpl): RemoteDatabaseRepository
}