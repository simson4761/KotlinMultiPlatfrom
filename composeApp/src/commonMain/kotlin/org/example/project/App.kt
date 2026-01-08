package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shared.network.ApiResponse.Error
import com.example.shared.network.ApiResponse.Success
import com.example.shared.viewModel.LoginViewModel
import newkotlinmultiplatform.composeapp.generated.resources.Res
import newkotlinmultiplatform.composeapp.generated.resources.compose_multiplatform
import org.example.project.data.LoginResponse
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val viewModel = LoginViewModel()
        val loginState by viewModel.loginState.collectAsState()

        LaunchedEffect(loginState){
            when(loginState){
                Success(null) -> {
                    println(
                        "LoginState : Login success: data null"
                    )
                }
                is Error -> {
                    println(
                        "LoginState Login failed: ${(loginState as Error).message}"
                    )
                }
                is Success<LoginResponse> -> {
                    val data = (loginState as Success<LoginResponse>).data

                    when (data) {
                        null -> {
                            println("LoginState : Login success with no data (Unit / empty response)")
                        }
                        is LoginResponse -> {
                            println(
                                "LoginState : Login success: token=${data.token}"
                            )
                        }

                        else -> {
                            println("LoginState : Login success with unknown data type")
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                viewModel.login()
            }) {
                Text("Click me to login")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}