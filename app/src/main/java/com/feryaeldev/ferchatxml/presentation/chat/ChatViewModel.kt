package com.feryaeldev.ferchatxml.presentation.chat

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feryaeldev.ferchatxml.domain.entity.Message
import com.feryaeldev.ferchatxml.domain.usecase.CloseSessionUseCase
import com.feryaeldev.ferchatxml.domain.usecase.GetMessagesUseCase
import com.feryaeldev.ferchatxml.domain.usecase.GetUsernameUseCase
import com.feryaeldev.ferchatxml.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getUsernameUseCase: GetUsernameUseCase,
    private val closeSessionUseCase: CloseSessionUseCase,
) : ViewModel() {

    private var _messageList = MutableStateFlow<List<Message>>(emptyList())
    val messageList: StateFlow<List<Message>> = _messageList

    var name: String = ""

    init {
        getUsername()
        getMessages()
    }

    private fun getUsername() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUsernameUseCase()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendMessage(message: String) {
        sendMessageUseCase(message, name)
    }

    fun getMessages() {
        viewModelScope.launch {
            val result = getMessagesUseCase()
            result.collect {
                _messageList.value = it
                Log.d("fer tutorial", "la info es $it")
            }
        }
    }

    fun closeSession(onCloseSessionFinish:() -> Unit) {
        viewModelScope.launch {
            async { closeSessionUseCase() }.await()
            onCloseSessionFinish()
        }
    }
}