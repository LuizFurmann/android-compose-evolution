package com.example.studycompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(val title:String, val icons: ImageVector, val screens:ComposableFun) {

    object Home : TabItem(title = "Home",icons= Icons.Default.Home, screens = { HomeScreen()})
    object Category: TabItem(title = "Category",icons = Icons.Default.ShoppingCart, screens={ CategoryScreen()})
    object Rate : TabItem(title = "Rate",icons= Icons.Default.AccountCircle, screens = { RateScreen()})


}