package com.sheansuke.kotlinmvvm.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.navigation.AuthenticationNavGraphRoutes
import com.sheansuke.kotlinmvvm.presentation.navigation.RootGraph
import com.sheansuke.kotlinmvvm.presentation.screens.login.LoginEvent
import com.sheansuke.kotlinmvvm.presentation.screens.login.LoginViewModel
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Darkgray700
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red500

@Composable
fun LoginContent(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        BoxHeader()
        CardForm(navController)
    }
}

@Composable
fun BoxHeader() {
    Box(
        modifier = Modifier
            .height(270.dp)
            .background(Red500)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(130.dp),
                painter = painterResource(id = R.drawable.control),
                contentDescription = "Control de xbox 360",
            )

            Text(
                modifier = Modifier.padding(bottom = 30.dp),
                text = "FIREBASE MVVM"
            )
        }

    }
}

@Composable
fun CardForm(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
val eventFlow = viewModel.eventFlow.collectAsState()

    Card(
        modifier = Modifier.padding(
            start = 40.dp,
            end = 40.dp,
            top = 200.dp,
            bottom = 0.dp
        ),
        backgroundColor = Darkgray700,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                text = "LOGIN",
                modifier = Modifier
                    .padding(top = 30.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,

                )
            Text(
                text = "Por favor inicia sesion para continuar",
                modifier = Modifier.padding(top = 10.dp, start = 0.dp, end = 0.dp, bottom = 20.dp),
                fontSize = 15.sp,
                color = Color.Gray,
            )

            DefaultTextField(
                value = viewModel.state.value.email,
                onValueChange = { viewModel.onEvent(LoginEvent.InputEmail(it)) },
                label = "Email",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email,
                errorMsg = if (viewModel.state.value.isValidEmail == false) "Email no valido" else ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            DefaultTextField(
                value = viewModel.state.value.password,
                onValueChange = { viewModel.onEvent(LoginEvent.InputPassword(it)) },
                label = "Password",
                icon = Icons.Default.Lock,
                hiddeText = true,
                keyboardType = KeyboardType.Password,
                errorMsg = if (viewModel.state.value.isValidPassword == false) "Password no valida" else ""
            )
            Spacer(modifier = Modifier.height(10.dp))


            DefaultButton(
                text = "INICIAR SESION",
                enabled = viewModel.state.value.isValidForm,
                onClick = {
                    viewModel.onEvent(LoginEvent.Login)
                },
//
            )
        }
    }

    eventFlow.value?.let {
        when (it) {
            is UiEvent.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiEvent.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(RootGraph.HOME) {
                        popUpTo(AuthenticationNavGraphRoutes.Login.routeName) { inclusive = true }
                    }

                }
            }

            is UiEvent.Error -> {
                Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_LONG).show()
            }

            else -> {}
        }
    }
}