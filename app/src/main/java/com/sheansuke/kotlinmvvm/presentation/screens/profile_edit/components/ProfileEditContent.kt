package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileViewModel
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(
     viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Red500)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier.padding(top = 20.dp),
                        text = "Editar Perfil",
                        fontSize = 30.sp
                    )

                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 120.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.padding(bottom = 50.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "user icon"
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = viewModel.state.value.username ?: "",
                    onValueChange = {},
                    label = "Username",
                    icon = Icons.Default.Face
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 20.dp, vertical = 0.dp),
                    icon = Icons.Default.Refresh,
                    text = "Actualizar Perfil",
                    onClick = {}
                )
            }

        }
    }
}
