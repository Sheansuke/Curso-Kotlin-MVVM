package com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultButton
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileEditEvent
import com.sheansuke.kotlinmvvm.presentation.screens.profile_edit.ProfileEditViewModel
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red500

@Composable
fun ProfileEditContent(
    viewModel: ProfileEditViewModel = hiltViewModel()
) {
    val eventFlow = viewModel.eventFlow.collectAsState()

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> uri?.let { viewModel.onEvent(ProfileEditEvent.PickImage(uri)) } })

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

                if (viewModel.state.value.image != null) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape),
                        model = viewModel.state.value.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user icon",
                    )
                }

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    value = viewModel.state.value.username ?: "",
                    onValueChange = {
                        viewModel.onEvent(ProfileEditEvent.InputUserName(it))
                    },
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
                    onClick = {
                        viewModel.onEvent(ProfileEditEvent.UpdateUser)
                    },
                    isLoading = if (eventFlow.value == UiEvent.Loading) true else false,
                    enabled = if (eventFlow.value == UiEvent.Loading) false else true,
                )
            }

        }
    }

}
