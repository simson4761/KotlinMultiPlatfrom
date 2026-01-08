package com.example.shared.network

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import org.example.project.data.LoginRequest
import org.example.project.data.LoginResponse

class ApiGatewayImpl : ApiGateway {
    companion object {
        private val client = ApiClient.createApiClient(NetworkConfig())
    }

    override suspend fun login(request: LoginRequest): ApiResponse<LoginResponse> {
        return try{
            val response =client.post("/auth/login") {
                setBody(request)
            }

            if (response.status.isSuccess()){
                ApiResponse.Success(response.body())
            }
            else{
                ApiResponse.Error(response.body(), responseCode = response.status.value)
            }
        }
        catch(e: Exception) {
            ApiResponse.Error(
                message = e.message ?: "Unknown error",
                exception = e
            )
        }
    }
}
