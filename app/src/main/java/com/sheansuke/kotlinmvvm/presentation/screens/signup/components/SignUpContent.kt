package com.sheansuke.kotlinmvvm.presentation.screens.signup.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.screens.signup.SignUpViewModel
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Darkgray500
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Darkgray700

@Composable
fun SignUpContent() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        SignUpContentHeader()
        SignUpContentBody()
    }
}


@Composable
fun SignUpContentHeader() {
    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.primary
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = R.drawable.user), contentDescription = "User Icon"
            )

        }
    }
}

@Composable
fun SignUpContentBody(viewModel: SignUpViewModel = hiltViewModel()) {

    var userName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(690.dp)
                .padding(
                    top = 140.dp,
                    bottom = 0.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            backgroundColor = Darkgray700
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Column() {
                    Text(
                        text = "REGISTRO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Por favor ingresa estos datos para continuar",
                        color = Darkgray500

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName,
                    onValueChange = { userName = it },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electronico",
                    icon = Icons.Default.Email
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    icon = Icons.Default.Lock
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirmar Password",
                    icon = Icons.Default.Lock
                )

                DefaultButton(
                    icon = Icons.Default.ArrowForward,
                    text = "INICIAR SESION", onClick = { /*TODO*/ })
            }
        }
    }
}