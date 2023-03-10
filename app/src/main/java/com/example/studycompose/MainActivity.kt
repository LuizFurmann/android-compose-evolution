package com.example.studycompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.base.R
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.example.studycompose.model.Movie
import com.example.studycompose.ui.theme.StudyComposeTheme
import com.example.studycompose.view.ItemListItem
import com.example.studycompose.viewModel.MovieViewModel
import com.google.accompanist.pager.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                         AppBar(
                             onNavigationIconClick = {
                                 scope.launch {
                                     scaffoldState.drawerState.open()
                                 }
                             }
                         )
                },

                drawerContent = {
                    DrawerHeader()
                    DrawrContent(
                        onItemClick ={
                            when(it.title){
                                "Message" -> startMessageActivity(this@MainActivity)
                                "Settings" -> Toast.makeText(this@MainActivity, "Tela de configurações", Toast.LENGTH_SHORT).show()
                            }
                    })
                }
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainScreen(movieList: List<Movie>, context: Context) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        ItemList(movieList,context, state = textState)
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        label = {
            Text("Pesquisar")
        },
        modifier = Modifier.fillMaxWidth()
            .padding(15.dp, 5.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    modifier = Modifier,
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = CircleShape, // The TextFiled has rounded corners top left and right by default
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Blue,
            leadingIconColor = Color.Blue,
            trailingIconColor = Color.Blue,
            backgroundColor = Color.White,
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
        )
    )
}

@Composable
fun ItemList(movieList: List<Movie>,context: Context, state: MutableState<TextFieldValue>) {

    var filteredItems: List<Movie>

    val viewModel: MovieViewModel = viewModel()

    LazyColumn(Modifier.padding(10.dp, 10.dp)) {

        filteredItems = viewModel.movieFilter(movieList, context, state)

        items(filteredItems) { filteredItem ->
            ItemListItem(
                ItemText = filteredItem,
                context = context
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContent() {

    val list = listOf(TabItem.Home,TabItem.Category,TabItem.Rate)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.primary,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->

            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {Text(tabItem.title) },
                icon = {Icon(imageVector = tabItem.icons,contentDescription = null)},
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.White,
                enabled = true
            )

        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem>,pagerState: PagerState) {
    HorizontalPager(count = tabs.size,state=pagerState) { page ->
        tabs[page].screens()
    }
}

@Composable
fun HomeScreen() {
    val movieViewModel: MovieViewModel = viewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainScreen(movieViewModel.getMovieList(),context)
        movieViewModel.getMovieList()

        if (movieViewModel.getMovieList().isNullOrEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.Cyan

                )
            }
        }
    }
}

@Composable
fun CategoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Cart Screen")
    }
}

@Composable
fun RateScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Profile Screen")
    }
}

fun startMessageActivity(context: Context){
    Intent(context, MessageActivity::class.java).also{
        context.startActivity(it)
    }
}
