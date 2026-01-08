package com.example.shared.network

import io.ktor.client.statement.HttpResponse
import org.example.project.data.LoginRequest
import org.example.project.data.LoginResponse

interface ApiGateway {
    suspend fun login(request: LoginRequest): ApiResponse<LoginResponse>

}