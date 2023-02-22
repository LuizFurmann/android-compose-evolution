package com.example.composeevolution

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeevolution.model.Movie
import com.example.composeevolution.ui.theme.ComposeEvolutionTheme
import com.example.composeevolution.view.MovieItem
import com.example.composeevolution.viewModel.MovieViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val movieVielModel by viewModels<MovieViewModel>()

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
                                "Setting" -> Toast.makeText(this@MainActivity, "Configurações", Toast.LENGTH_SHORT).show()
                            }
                        })
                }
            ) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieList(movieList = movieVielModel.movieListResponse, this)
                    movieVielModel.getMovieList()
                }
            }
        }
    }
}

@Composable
fun MovieList(movieList: List<Movie>, context: Context) {
    LazyColumn(Modifier.padding(10.dp, 10.dp)) { //marginTop
        itemsIndexed(items = movieList) { index, item ->
            MovieItem(movie = item, context)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(){
    val tabList = listOf(
        TabItem.Home,
        TabItem.Contact,
        TabItem.Account
    )

    //youtube.com/watch?v=lRCm78CyV-A
    val pagerState = rememberPagerState(pageCount = tabList.size)

    Scaffold (
        topBar = { TopBar() }
    ){
        Tabs(tabs = tabList, pagerState = pagerState)
        TabsContent(tabs = tabList, pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState){
    HorizontalPager(state = pagerState) { page ->
        tabs[page].sreen

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState){
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage,
    backgroundColor = Color.Green,
    contentColor = Color.Black,
    indicator = {tabPositions ->
        TabRowDefaults.Indicator(
            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
    }
    ) {
        tabs.forEachIndexed{index, tab ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "")},
                text = { Text(text = tab.title)},
                selected = pagerState.currentPage == index,
                onClick = {
                          scope.launch{
                              pagerState.animateScrollToPage(index)
                          }
                },
            )
        }
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = "Tabs", fontSize = 18.sp) },
        backgroundColor = Color.Gray,
        contentColor = Color.Green
    )
}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home",
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        fontSize = 20.sp
        )
    }
}

@Composable
fun ContactScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contatos",
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            fontSize = 20.sp
        )
    }
}

@Composable
fun AccountsScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contas",
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            fontSize = 20.sp
        )
    }
}

fun startMessageActivity(context: Context){
    Intent(context, MessageActivity::class.java).also{
        context.startActivity(it)
    }
}