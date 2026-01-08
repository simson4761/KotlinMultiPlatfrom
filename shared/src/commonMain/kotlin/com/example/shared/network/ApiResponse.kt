package com.example.shared.network

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(
        val data: T?
    ) : ApiResponse<T>()

    data class Error(
        val message : String,
        val exception: Throwable? = null,
        val responseCode: Int = -1
    ) : ApiResponse<Nothing>()
}