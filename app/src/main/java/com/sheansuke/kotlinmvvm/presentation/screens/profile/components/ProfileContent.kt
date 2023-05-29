package com.sheansuke.kotlinmvvm.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.navigation.AppScreen
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileEvent
import com.sheansuke.kotlinmvvm.presentation.screens.profile.ProfileViewModel
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

// TODO: falta pulir lo de las imagenes de perfil
@Composable
fun ProfileContent(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val eventFlow = viewModel.eventFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "background",
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = "BIENVENIDO",
                    fontSize = 30.sp
                )
                Image(
                    modifier = Modifier.padding(top = 70.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "user icon"
                )
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontSize = 25.sp,
                text = viewModel.state.value.username ?: ""
            )
            Text(
                color = Color.Gray,
                text = viewModel.state.value.email ?: ""
            )
        }

        Column() {
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp, vertical = 0.dp),
                text = "Editar Datos",
                icon = Icons.Default.Edit,
                color = Color.White,
                onClick = {
                    viewModel.state.value.image = URLEncoder.encode(
                        viewModel.state.value.image,
                        StandardCharsets.UTF_8.toString()
                    )
                    navController.navigate(AppScreen.ProfileEdit.passUser(viewModel.state.value.toJson()))
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp, vertical = 0.dp),
                icon = Icons.Default.ExitToApp,
                text = "Cerrar Sesion",
                onClick = {
                    viewModel.onEvent(ProfileEvent.Logout)
                })
        }
    }


    eventFlow.value.let {
        when (it) {
            is UiEvent.Loading -> {}
            is UiEvent.Logout -> {
                LaunchedEffect(Unit) {
                    navController.navigate(AppScreen.Login.routeName, {
                        popUpTo(AppScreen.Profile.routeName, { inclusive = true })
                    })
                }
            }

            else -> {}
        }
    }
}