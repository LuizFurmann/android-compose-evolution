package com.example.composeevolution

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon : Int, var title: String, var sreen: ComposableFun){
    object Home: TabItem(R.drawable.ic_home, "Home", { HomeScreen() })
    object Contact: TabItem(R.drawable.ic_contact, "Contatos", { ContactScreen() })
    object Account: TabItem(R.drawable.ic_account, "Contas", { AccountsScreen() })
}
