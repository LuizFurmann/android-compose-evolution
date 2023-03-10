package com.example.studycompose.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.studycompose.DetailsActivity
import com.example.studycompose.R
import com.example.studycompose.model.Movie

@Composable
fun ItemListItem(ItemText: Movie,context: Context) {
    Card(modifier = Modifier.clickable {
        Intent(context, DetailsActivity::class.java).also{
            it.putExtra("movie", ItemText)
            context.startActivity(it)
        }
    }
        .padding(8.dp, 5.dp)
        .fillMaxWidth()
        .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    )

    {
        Surface(){
            Row(Modifier.padding(4.dp)){
                Image(painter = rememberImagePainter(data = ItemText.imageUrl,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(coil.base.R.drawable.notification_action_background)
                        transformations(CircleCropTransformation())
                    }),
                    contentDescription = ItemText.desc,
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
                        text = ItemText.name,
                        style = MaterialTheme.typography.subtitle1,
                    )

                    Text(
                        text = ItemText.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )

                    Text(
                        text = ItemText.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}