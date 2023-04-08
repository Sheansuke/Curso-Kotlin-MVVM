package com.sheansuke.kotlinmvvm.presentation.screens.signup.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SignUpTopBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row() {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Nuevo Usuario",
                fontWeight = FontWeight.Bold,
                )
        }
    }
}