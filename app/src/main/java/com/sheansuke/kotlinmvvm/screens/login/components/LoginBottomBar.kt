package com.sheansuke.kotlinmvvm.screens.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
        ,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Tienes una cuenta?", fontSize = 14.sp, color = Color.Gray)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "REGISTRATE AQUI",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
    }
}