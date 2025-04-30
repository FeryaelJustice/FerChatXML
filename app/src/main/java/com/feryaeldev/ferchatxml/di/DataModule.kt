package com.feryaeldev.ferchatxml.di

import android.content.Context
import com.feryaeldev.ferchatxml.data.local.service.DatastoreService
import com.feryaeldev.ferchatxml.data.remote.service.FirebaseDatabaseService
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideDatabaseReference() = Firebase.database.reference

    @Provides
    fun provideFirebaseDatabaseService(reference: DatabaseReference) = FirebaseDatabaseService(reference)

    @Provides
    fun provideDatastoreService(@ApplicationContext context: Context) = DatastoreService(context)
}