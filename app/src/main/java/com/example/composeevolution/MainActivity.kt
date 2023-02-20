package com.example.composeevolution

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.composeevolution.ui.theme.ComposeEvolutionTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
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