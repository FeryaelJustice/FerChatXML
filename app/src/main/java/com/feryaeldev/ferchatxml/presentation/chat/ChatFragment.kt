package com.feryaeldev.ferchatxml.presentation.chat

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.feryaeldev.ferchatxml.R
import com.feryaeldev.ferchatxml.databinding.FragmentChatBinding
import com.feryaeldev.ferchatxml.domain.entity.Message
import com.feryaeldev.ferchatxml.presentation.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels()

    private lateinit var chatAdapter: ChatAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.ivBack.setOnClickListener {
            logout()
        }
        binding.btnSendMsg.setOnClickListener {
            val msg = binding.etChat.text.toString()
            if (msg.isNotEmpty()) {
                viewModel.sendMessage(msg)
            }
            binding.etChat.text.clear()
        }
        binding.ivCloseSession.setOnClickListener {
           logout()
        }
        setupUI()
        return binding.root
    }

    private fun logout(){
        viewModel.closeSession(onCloseSessionFinish = {
            findNavController().navigate(R.id.action_back)
        })
    }

    private fun setupUI() {
        setupMessages()
        subscribeToMessages()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.tvTitle.text = viewModel.name
    }

    private fun setupMessages() {
        chatAdapter = ChatAdapter(mutableListOf<Message>())
        binding.rvMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                setupToolbar()
                chatAdapter.updateList(it.toMutableList(), viewModel.name)
                binding.rvMsg.scrollToPosition(chatAdapter.messagesList.size - 1)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.closeSession {
                        findNavController().navigate(R.id.action_back)
                    }
                }
            }
        )
    }
}