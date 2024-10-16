package com.example.eprice.ui


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.eprice.R
import com.example.eprice.model.Eprice
import com.example.eprice.viewmodel.AikaViewModel
import com.example.eprice.ui.theme.EpriceTheme
import com.example.eprice.viewmodel.EpriceViewModel


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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
        composable(route = "Aika") {
            TimeScreen(navController)
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = stringResource(R.string.s_hk_n_hinta_nyt)) },

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
                    text = { Text("Aika")},
                    onClick={navController.navigate("aika")})

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
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF8a2be2) ,
            titleContentColor = Color.White
        )

    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController,epriceViewModel: EpriceViewModel = viewModel()) {
    val eprice by epriceViewModel.eprice.observeAsState()
    Scaffold (
        topBar = { MainTopBar(title = "Sähkön hinta nyt", navController = navController) },
        content = { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.s_hk_n_hinta), fontSize = 24.sp)
                eprice?.let { price ->
                    priceList(price)
                } ?: CircularProgressIndicator() // Replace "Loading..." with a CircularProgressIndicator
                Text(text = stringResource(R.string.centti), fontSize = 24.sp)
            }
        }
    )
}

@Composable
fun priceList(eprice: Eprice) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "${eprice.price}", fontSize = 24.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),

        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeScreen(navController: NavController,todoViewModel: AikaViewModel = viewModel() ) {
    Scaffold (
        topBar = { ScreenTopBar("Aika",navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                timeStamp()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun timeStamp() {
    val dateAndHour = AikaViewModel.getCurrentDateHourAndMinute()
    Column(Modifier
        .padding(24.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(4.dp))
        Text(
                text="$dateAndHour",
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top=24.dp,bottom=24.dp)

            )
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(4.dp))

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