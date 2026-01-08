package org.example.project.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.example.project.data.LoginRequest
import org.example.project.data.LoginResponse

class ApiGatewayImpl(
    private val client: HttpClient
) : ApiGateway {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return client.post("/auth/login") {
            setBody(request)
        }.body()
    }

    override suspend fun logOut(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}
