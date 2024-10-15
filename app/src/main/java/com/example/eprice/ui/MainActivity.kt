package com.example.eprice.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.eprice.model.TodoViewModel
import com.example.eprice.ui.theme.EpriceTheme
import com.example.viewmodel.EpriceViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EpriceTheme {
                EpriceApp()
            }
        }
    }
}

@Composable
fun EpriceApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info") {
            TestScreen(navController)
        }
        composable(route = "Settings") {
            SettingsScreen(navController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "Sähkön hinta nyt") },

        actions = {
            IconButton(
                onClick = {expanded=!expanded}
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null, tint = Color.White)
            }
            DropdownMenu(
                expanded=expanded,
                onDismissRequest = {expanded=false}) {
                DropdownMenuItem(
                    text = { Text("info")},
                    onClick={navController.navigate("info")})


                DropdownMenuItem(
                    text = { Text("settings")},
                    onClick={navController.navigate("settings")})
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = {navController.navigateUp()}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF90EE90) ,
            titleContentColor = Color.Black
        )

    )
}

@Composable
fun MainScreen(navController: NavController,epriceViewModel: EpriceViewModel = viewModel()) {
    Scaffold (
        topBar = { MainTopBar("Sähkön hinta nyt",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Sähkön hinta")
                priceList(epriceViewModel.todos)
            }
        }
    )
}

@Composable
fun priceList(todos:List<String>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(todos) {todo ->
            Text(
                text=todo,
                modifier = Modifier.padding(top=4.dp,bottom=4.dp)
            )
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
fun TestScreen(navController: NavController,todoViewModel: TodoViewModel = viewModel() ) {
    Scaffold (
        topBar = { ScreenTopBar("Testisivu",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Saa nähä ...")
                modoList(todoViewModel.modos)
            }
        }
    )
}

@Composable
fun modoList(modos:List<String>) {
    LazyColumn(
        modifier = Modifier.padding(28.dp)
    ) {
        items(modos) {todo ->
            Text(
                text=todo,
                modifier = Modifier.padding(top=4.dp,bottom=24.dp)
            )
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(4.dp))
        }
    }
}



@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold (
        topBar = { ScreenTopBar("Settings",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Content for settings screen")
            }
        }
    )
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EpriceTheme {
        Greeting("Android")
    }
}