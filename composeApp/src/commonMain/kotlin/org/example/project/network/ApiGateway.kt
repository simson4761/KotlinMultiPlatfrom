package org.example.project.network

import org.example.project.data.LoginRequest
import org.example.project.data.LoginResponse

interface ApiGateway {
    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun logOut(request: LoginRequest): LoginResponse

}