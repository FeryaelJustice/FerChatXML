package com.feryaeldev.ferchatxml.presentation.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.feryaeldev.ferchatxml.databinding.ItemChatMeBinding
import com.feryaeldev.ferchatxml.databinding.ItemChatOtherBinding
import com.feryaeldev.ferchatxml.domain.entity.Message

class ChatAdapter(var messagesList: MutableList<Message>, private var userName: String = "") :
    RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
        const val SENT_MESSAGE = 0
        const val RECEIVED_MESSAGE = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {
        val binding = when (viewType) {
            SENT_MESSAGE -> ItemChatMeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            RECEIVED_MESSAGE -> ItemChatOtherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

            else -> throw IllegalArgumentException("Invalid view type")
        }
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {
        holder.bind(messagesList[position], getItemViewType(position))
    }

    override fun getItemCount(): Int = messagesList.size

    override fun getItemViewType(position: Int): Int {
        return if (messagesList[position].user.userName.equals(userName, ignoreCase = false)) {
            SENT_MESSAGE
        } else {
            RECEIVED_MESSAGE
        }
    }

    fun updateList(messages: MutableList<Message>, name: String) {
        userName = name

        messagesList.clear()

        var previousDate: String? = null
        for (msg in messages) {
            msg.showDate = msg.date != previousDate
            previousDate = msg.date
        }

        messagesList.addAll(messages)
        notifyItemInserted(messagesList.size - 1)
    }
}