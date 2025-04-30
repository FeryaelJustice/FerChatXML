package com.feryaeldev.ferchatxml.presentation.chat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.feryaeldev.ferchatxml.databinding.ItemChatMeBinding
import com.feryaeldev.ferchatxml.databinding.ItemChatOtherBinding
import com.feryaeldev.ferchatxml.domain.entity.Message

class ChatViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message, itemViewType: Int) {
        when (itemViewType) {
            ChatAdapter.SENT_MESSAGE -> bindSentMessage(message)
            ChatAdapter.RECEIVED_MESSAGE -> bindReceivedMessage(message)
        }
    }

    private fun bindReceivedMessage(message: Message) {
        val currentBinding = binding as ItemChatOtherBinding
        currentBinding.tvDateOther.text = message.date
        currentBinding.tvDateOther.visibility = if (message.showDate) View.VISIBLE else View.GONE
        currentBinding.tvChatOther.text = message.msg
        currentBinding.tvNameOther.text = message.user.userName
        currentBinding.tvHourOther.text = message.hour
        currentBinding.ivIsAdminOther.visibility =
            if (message.user.admin) View.VISIBLE else View.GONE
    }

    private fun bindSentMessage(message: Message) {
        val currentBinding = binding as ItemChatMeBinding
        currentBinding.tvDate.text = message.date
        currentBinding.tvDate.visibility = if (message.showDate) View.VISIBLE else View.GONE
        currentBinding.tvChat.text = message.msg
        currentBinding.tvHour.text = message.hour
        currentBinding.ivIsAdmin.visibility = if (message.user.admin) View.VISIBLE else View.GONE
    }
}