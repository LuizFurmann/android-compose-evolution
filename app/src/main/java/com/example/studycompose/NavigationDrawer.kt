package com.example.studycompose

import android.view.MenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = "BRQ", fontSize = 60.sp)
    }
}

@Composable
fun DrawrContent(
    modifier: Modifier = Modifier,
    onItemClick: (DrawrContent) -> Unit
) {
    val list = listOf<DrawrContent>(
        DrawrContent(id = 1, title = "Message", imageVector = Icons.Default.Email),
        DrawrContent(id = 2,title = "Settings", imageVector = Icons.Default.Settings),
    )
    LazyColumn(modifier) {
        items(list) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
            ) {
                Icon(
                    imageVector = item.imageVector,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = item.title,
                    style = TextStyle(color = Color.Black, fontSize = 20.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

data class DrawrContent(val id: Int, val title: String, val imageVector: ImageVector)