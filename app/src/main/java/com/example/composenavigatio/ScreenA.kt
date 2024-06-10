package com.example.composenavigatio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun ScreenA(
    navigationToScreenB: (Person) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var id by remember { mutableStateOf("") }

        TextField(
            value = name,
            onValueChange = { name = it },
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = age,
            onValueChange = { age = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = id,
            onValueChange = { id = it },
        )

        Button(
            onClick = { navigationToScreenB(
                Person(
                    name = name,
                    age = age.toInt(),
                    id = id.toInt()
                )
              )
            }
        ) {
            Text("Navigate to Screen B")
        }
    }
}