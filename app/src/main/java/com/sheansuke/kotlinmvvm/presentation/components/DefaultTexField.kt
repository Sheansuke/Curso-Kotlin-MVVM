package com.sheansuke.kotlinmvvm.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector?,
    keyboardType: KeyboardType = KeyboardType.Text,
    hiddeText: Boolean = false,
    validator: () -> Unit = {},
    errorMsg: String = ""
) {
    Column() {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = {
                icon?.let {
                    Icon(imageVector = icon, contentDescription = "Lock Icon")

                }
            },
            label = {
                Text(text = label)
            },
            onValueChange = {
                onValueChange(it)
                validator()
            },
            visualTransformation = if (hiddeText) PasswordVisualTransformation() else VisualTransformation.None
        )
        Text(text = errorMsg)
    }
}