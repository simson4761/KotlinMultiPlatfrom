package com.example.shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shared.network.ApiGatewayImpl
import com.example.shared.network.ApiResponse
import io.ktor.client.call.body
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.LoginRequest
import org.example.project.data.LoginResponse

class LoginViewModel() : ViewModel() {
    private val _loginState  = MutableStateFlow<ApiResponse<LoginResponse>>(ApiResponse.Success(null))
    val loginState = _loginState.asStateFlow()

    fun login(){
        viewModelScope.launch {
            _loginState.value  = ApiGatewayImpl().login(
                request = LoginRequest(
                    username = "username",
                    password = "password"
                )
            )
        }
    }
}