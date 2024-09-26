package com.example.contactapp.utils

sealed class ResultState<out T> {
    data object Loading : ResultState<Nothing>()
    data object Uninitialised : ResultState<Nothing>()
    data object Empty : ResultState<Nothing>()
    data class Success<T> (val result : T) : ResultState<T>()
    data class Error (val message : String) : ResultState<Nothing>()
}