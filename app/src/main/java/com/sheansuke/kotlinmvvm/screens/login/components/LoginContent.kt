package com.sheansuke.kotlinmvvm.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheansuke.kotlinmvvm.R
import com.sheansuke.kotlinmvvm.ui.theme.Darkgray500

@Composable
fun LoginContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.height(130.dp),
            painter = painterResource(id = R.drawable.control),
            contentDescription = "Control de xbox 360",
        )
        Text(
            modifier = Modifier.padding(bottom = 30.dp),
            text = "FIREBASE MVVM")

        CardForm()
    }
}

@Composable
fun CardForm() {
    Card(
        modifier = Modifier.padding(
            horizontal = 40.dp,
        ),
        backgroundColor = Darkgray500,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "LOGIN",
                modifier = Modifier
                    .padding(top = 30.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,

                )
            Text(
                text = "Por favor inicia sesion para continuar",
                modifier = Modifier.padding(vertical = 12.dp),
                fontSize = 15.sp,
                color = Color.Gray,
            )

            OutlinedTextField(
                value = "",
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon")
                },
                label = {
                    Text(text = "Email")
                },
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "",
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                },
                label = {
                    Text(text = "Password")
                },
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),


                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "arrow button")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "INICIAR SESION")
            }
        }
    }
}