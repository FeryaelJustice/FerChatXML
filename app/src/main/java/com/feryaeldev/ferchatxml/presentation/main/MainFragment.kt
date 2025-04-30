package com.feryaeldev.ferchatxml.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.feryaeldev.ferchatxml.R
import com.feryaeldev.ferchatxml.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.btnChat.setOnClickListener {
            if (!binding.tiedName.text.isNullOrEmpty()) {
                viewModel.saveUsername(binding.tiedName.text.toString())
                findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
            }
        }
        subscribeToState()
        return binding.root
    }

    private fun subscribeToState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    MainViewState.LOADING -> {
                        binding.pbLoading.visibility = View.VISIBLE
                    }

                    MainViewState.UNREGISTERED -> {
                        binding.pbLoading.visibility = View.GONE
                    }

                    MainViewState.REGISTERED -> {
                        binding.pbLoading.visibility = View.GONE
                        findNavController().navigate(R.id.action_main_fragment_to_chat_fragment)
                    }

                    is MainViewState.ERROR -> binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
            }
        )
    }

}