package com.example.composeevolution.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.composeevolution.DetailsActivity
import com.example.composeevolution.model.Movie


@Composable
fun MovieItem(movie: Movie, context: Context){
    val msg = "Hello"

    Card(modifier = Modifier.clickable {
        Intent(context, DetailsActivity::class.java).also{
            context.startActivity(it)
        }
    }
        .padding(8.dp, 10.dp)
        .fillMaxWidth()
        .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp)


    {
        Surface(){
            Row(Modifier.padding(4.dp)){
                Image(painter = rememberImagePainter(data = movie.imageUrl,
                builder = {
                    scale(Scale.FILL)
                    placeholder(coil.base.R.drawable.notification_action_background)
                    transformations(CircleCropTransformation())
                }),
                    contentDescription = movie.desc,
                    Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = movie.name,
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Text(
                        text = movie.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )

                    Text(
                        text = movie.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}