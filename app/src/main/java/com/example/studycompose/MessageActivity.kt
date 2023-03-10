package com.example.studycompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studycompose.ui.theme.StudyComposeTheme

class MessageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                        .fillMaxSize()
                    ) {
                        Text("Write your message...", fontSize = 20.sp,
                        modifier = Modifier
                            .padding(20.dp))
                        inputFields(this@MessageActivity)
                    }
                }
            }
        }
    }
}

@Composable
fun inputFields(context: Context){

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value)
        Dialog(context, setShowDialog = {
            showDialog.value = it
        })

    var username by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(20.dp),
        backgroundColor = Color.LightGray
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp)) {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text("Username")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Red,
                    backgroundColor = White)
            )

            OutlinedTextField(
                value = message,
                onValueChange = {
                    message = it
                },
                label = {
                    Text("Message")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Green,
                    unfocusedBorderColor = Yellow,
                    backgroundColor = White)
            )
            
            Button(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {
                    showDialog.value = true

//                Toast.makeText(context, "Sending...", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Send", color = Color.White)
            }
        }
    }
}