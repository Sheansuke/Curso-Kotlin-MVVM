package com.sheansuke.kotlinmvvm.presentation.screens.signup.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.domain.model.Resource
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.navigation.AppScreen
import com.sheansuke.kotlinmvvm.presentation.screens.signup.SignUpEvent
import com.sheansuke.kotlinmvvm.presentation.screens.signup.SignUpViewModel
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Darkgray500
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Darkgray700

@Composable
fun SignUpContent(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        SignUpContentHeader()
        SignUpContentBody(navController)
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
fun SignUpContentBody(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val stateFlow = viewModel.signUpFlow.collectAsState()


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
                    value = viewModel.signUpState.value.username,
                    onValueChange = { viewModel.onEvent(SignUpEvent.InputUserName(it)) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    KeyboardType.Text,
                    errorMsg = if (viewModel.signUpState.value.isValidUserName == false) "El usuario necesita minimo 6 caracteres" else ""
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.signUpState.value.email,
                    onValueChange = { viewModel.onEvent(SignUpEvent.InputEmail(it)) },
                    label = "Correo electronico",
                    icon = Icons.Default.Email,
                    KeyboardType.Email,
                    errorMsg = if (viewModel.signUpState.value.isValidEmail == false) "Email no valido" else ""
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.signUpState.value.password,
                    onValueChange = { viewModel.onEvent(SignUpEvent.InputPassword(it)) },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    hiddeText = true,
                    keyboardType = KeyboardType.Password,
                    errorMsg = if (viewModel.signUpState.value.isValidPassword == false) "La password necesita minimo 8 caracteres" else ""
                )
                Spacer(modifier = Modifier.height(2.dp))
                DefaultTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.signUpState.value.confirmPassword,
                    onValueChange = { viewModel.onEvent(SignUpEvent.InputConfirmPassword(it)) },
                    label = "Confirmar Password",
                    icon = Icons.Default.Lock,
                    hiddeText = true,
                    keyboardType = KeyboardType.Password,
                    errorMsg = if (viewModel.signUpState.value.isValidConfirmPassword == false) "La password es distinta" else ""

                )

                DefaultButton(
                    icon = Icons.Default.ArrowForward,
                    enabled = viewModel.signUpState.value.isValidForm,
                    text = "Registrarse", onClick = {
                        viewModel.onEvent(SignUpEvent.SignUp)
                    })
            }
        }
    }

    stateFlow?.value.let {
        when (it) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                Toast.makeText(LocalContext.current, "Usuario Logeado", Toast.LENGTH_SHORT).show()

                LaunchedEffect(Unit) {
                    viewModel.onSignUp()
                    navController.navigate(AppScreen.Profile.routeName) {
                        popUpTo(AppScreen.SignUp.routeName) { inclusive = true }
                    }
                }
            }

            is Resource.Error -> {
                Toast.makeText(LocalContext.current, it.exception.message, Toast.LENGTH_SHORT)
                    .show()
            }

            else -> {}
        }
    }

}