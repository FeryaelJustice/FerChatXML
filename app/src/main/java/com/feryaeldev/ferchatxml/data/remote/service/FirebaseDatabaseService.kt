package com.feryaeldev.ferchatxml.data.remote.service

import com.feryaeldev.ferchatxml.data.remote.entity.dto.MessageDto
import com.feryaeldev.ferchatxml.data.remote.entity.response.MessageResponse
import com.feryaeldev.ferchatxml.data.remote.mapper.toBusiness
import com.feryaeldev.ferchatxml.domain.entity.Message
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseDatabaseService @Inject constructor(private val reference: DatabaseReference) {

    companion object {
        private const val PATH = "messages"
    }

    fun sendMsg(message: MessageDto) {
        reference.child(PATH).push().setValue(message)
    }

    fun getMessages(): Flow<List<Message>> {
        return reference.child(PATH).snapshots.map { dataSnapshot ->
            dataSnapshot.children.mapNotNull {
                it.getValue(MessageResponse::class.java)?.toBusiness()
            }
        }
    }
}