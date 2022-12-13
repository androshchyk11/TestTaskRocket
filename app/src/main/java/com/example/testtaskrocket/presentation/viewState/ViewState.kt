package com.example.testtaskrocket.presentation.viewState

sealed class ViewState<out T : Any?> {
    object Loading : ViewState<Nothing>()
    object Default : ViewState<Nothing>()
    class Success<out T : Any>(val result: T) : ViewState<T>()
    class Error(val errorInfo: Throwable) : ViewState<Nothing>()
}