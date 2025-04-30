package com.feryaeldev.ferchatxml.presentation.main

sealed class MainViewState {
    object UNREGISTERED : MainViewState()
    object REGISTERED : MainViewState()
    object LOADING : MainViewState()
    data class ERROR(val message: String) : MainViewState()
}