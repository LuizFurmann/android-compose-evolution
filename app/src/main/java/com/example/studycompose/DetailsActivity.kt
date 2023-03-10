package com.example.studycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import coil.compose.rememberImagePainter
import com.example.studycompose.model.Movie
import com.example.studycompose.ui.theme.StudyComposeTheme

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            var movie = intent.getSerializableExtra("movie") as Movie

            StudyComposeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    showDetails(movie)
                }
            }
        }
    }
}

@Composable
fun showDetails(movie: Movie){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.LightGray)
            .padding(20.dp)
    ) {
        Image(painter = rememberImagePainter(data = movie.imageUrl),
            contentDescription = movie.desc,
            Modifier
                .fillMaxHeight()
                .weight(0.2f)
        )
        Card(
            elevation = 4.dp,
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(movie.name, fontWeight = FontWeight.W700)
                Text(movie.desc)
                Text(movie.category, fontWeight = FontWeight.W300)
            }
        }
    }
}