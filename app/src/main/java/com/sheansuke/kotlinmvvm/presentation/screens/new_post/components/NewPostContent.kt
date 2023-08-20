package com.sheansuke.kotlinmvvm.presentation.screens.new_post.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.NewPostEvent
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.NewPostViewModel
import com.sheansuke.kotlinmvvm.presentation.screens.new_post.PostCategoryRadioButton
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red200

@Composable
fun NewPostContent() {
    Column() {
        Header()
        Body()

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = 20.sp, text = "CATEGORIAS"
            )
        }
        RadioButtonGroup()
    }
}

@Composable
fun Header(
    viewModel: NewPostViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val newPostEvent = remember {
        viewModel::onEvent
    }

    val singlePhotoPickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri -> uri?.let { newPostEvent(NewPostEvent.PickGameImageUri(uri)) } })
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red200)
            .height(220.dp)
            .clickable {
                singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.value.imageUri != null) {
                AsyncImage(
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    model = state.value.imageUri,
                    contentDescription = "Picked Image New Post"
                )
            } else {
                Image(
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = R.drawable.add_image),
                    contentDescription = "Add Image"
                )
            }
            Text(text = "SELECCIONA UNA IMAGEN")
        }
    }
}

@Composable
fun Body(
    viewModel: NewPostViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    val newPostEvent = remember() {
        viewModel::onEvent
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTextField(
            Modifier.fillMaxWidth(), value = state.value.name ?: "", onValueChange = {
                newPostEvent(NewPostEvent.InputGameName(it))
            }, label = "Nombre del juego", icon = Icons.Default.Face
        )
        DefaultTextField(
            Modifier.fillMaxWidth(), value = state.value.description ?: "", onValueChange = {
                newPostEvent(NewPostEvent.InputGameDescription(it))
            }, label = "Descripcion", icon = Icons.Default.List
        )

    }
}

@Composable
fun RadioButtonGroup(
    viewModel: NewPostViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val newPostEvent = remember() {
        viewModel::onEvent
    }

    val radioButtonItems = remember {
        listOf(
            PostCategoryRadioButton(
                "PC", R.drawable.icon_pc
            ), PostCategoryRadioButton(
                "PS4", R.drawable.icon_ps4
            ), PostCategoryRadioButton(
                "XBOX", R.drawable.icon_xbox
            ), PostCategoryRadioButton(
                "NINTENDO", R.drawable.icon_nintendo
            ), PostCategoryRadioButton(
                "MOBILE", R.drawable.icon_pc
            )
        ).toMutableList()
    }
    LazyColumn() {
        items(items = radioButtonItems, key = { it.name }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                RadioButton(selected = it.name == state.value.category, onClick = {
                    newPostEvent(NewPostEvent.SelectGameCategory(it.name))
                })
                Row() {
                    Text(
                        modifier = Modifier.width(100.dp), text = it.name
                    )
                    Spacer(modifier = Modifier.width(150.dp))
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = it.icon),
                        contentDescription = it.name
                    )
                }

            }
        }
    }

}