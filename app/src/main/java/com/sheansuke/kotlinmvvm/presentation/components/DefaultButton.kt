package com.sheansuke.kotlinmvvm.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sheansuke.kotlinmvvm.presentation.ui.theme.Red500

@Composable
fun DefaultButton(
    text: String,
    onClick: () -> Unit,
    color: Color = Red500,
    icon: ImageVector? = null,
    modifier: Modifier? = null,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier ?: Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 30.dp),
        colors = ButtonDefaults.buttonColors(color),
        enabled = enabled,
        onClick = { onClick() }
    ) {
        icon?.let {
            Icon(imageVector = icon, contentDescription = "arrow button")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}