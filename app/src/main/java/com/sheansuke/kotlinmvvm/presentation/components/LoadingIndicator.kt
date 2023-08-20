package com.sheansuke.kotlinmvvm.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sheansuke.kotlinmvvm.presentation.screens.utils.UiEvent

@Composable
fun LoadingIndicator(
    state: State<UiEvent?>,
    successMessage: String,
    errorMessage: String,
    onSuccess: () -> Any? = {},
    onError: () -> Any? = {},
) {

    val localContext = LocalContext.current
    val localContextState = remember {
        localContext
    }

    when (state.value) {
        is UiEvent.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiEvent.Success -> {
            LaunchedEffect(Unit) {
                Toast.makeText(localContextState, successMessage, Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        }

        is UiEvent.Error -> {
            LaunchedEffect(Unit) {
                Toast.makeText(localContextState, errorMessage, Toast.LENGTH_SHORT).show()
                onError()
            }
        }

        else -> {}
    }

}