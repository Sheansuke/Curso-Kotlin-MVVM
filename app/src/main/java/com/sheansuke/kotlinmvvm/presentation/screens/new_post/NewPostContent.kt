package com.sheansuke.kotlinmvvm.presentation.screens.new_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.presentation.components.DefaultTextField
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red200

@Composable
fun NewPostContent() {
    Column {
        Header()
        Body()

        Row(
            modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.Center
        ) {
            Text(
                fontSize = 20.sp,
                text = "CATEGORIAS"
            )
        }
        RadioButtonGroup()
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red200)
            .padding(bottom = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.add_image),
                contentDescription = "Add Image"
            )
            Text(text = "SELECCIONA UNA IMAGEN")
        }
    }
}

@Composable
fun Body() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultTextField(
            Modifier.fillMaxWidth(),
            value = "", onValueChange = {}, label = "Nombre del juego", icon = Icons.Default.Face
        )
        DefaultTextField(
            Modifier.fillMaxWidth(),
            value = "", onValueChange = {}, label = "Descripcion", icon = Icons.Default.List
        )
    }
}

@Composable
fun RadioButtonGroup() {
    val radioButtonItems = listOf(
        PostCategoryRadioButton(
            "PC",
            R.drawable.icon_pc
        ),
        PostCategoryRadioButton(
            "PS4",
            R.drawable.icon_ps4
        ),
        PostCategoryRadioButton(
            "XBOX",
            R.drawable.icon_xbox
        ),
        PostCategoryRadioButton(
            "NINTENDO",
            R.drawable.icon_nintendo
        ),
        PostCategoryRadioButton(
            "MOBILE",
            R.drawable.icon_pc
        )
    )

    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        radioButtonItems.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = false, onClick = { /*TODO*/ })
                Row() {
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = it.name
                    )
                    Spacer(modifier = Modifier.width(150.dp))
                    Image(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = it.icon), contentDescription = it.name
                    )
                }

            }
        }
    }
}