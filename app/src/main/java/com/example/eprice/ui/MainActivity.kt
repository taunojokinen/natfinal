package com.example.eprice.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
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
            InfoScreen(navController)
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
fun MainScreen(navController: NavController) {
    Scaffold (
        topBar = { MainTopBar("Sähkön hinta nyt",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Sähkön hinta nyt")
                ToDoList(EpriceViewModel.todos)
            }
        }
    )
}

fun ToDoList(todos:List<String>) {}

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold (
        topBar = { ScreenTopBar("Info",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text(text = "Content for info screen")
            }
        }
    )
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