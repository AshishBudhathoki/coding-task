package com.ashish.codingtask

import WeatherListingsScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ashish.codingtask.navigation.Route
import com.ashish.codingtask.theme.WeatherListingsTheme
import com.ashish.weather_presentation.weather_info.WeatherItemScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherListingsTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WEATHER_HOME
                    )
                    {
                        composable(Route.WEATHER_HOME) {
                            WeatherListingsScreen(
                                onNavigateToSearch = { id ->
                                    navController.navigate(
                                        Route.WEATHER_ITEM + "/$id"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.WEATHER_ITEM + "/{id}",
                            arguments = listOf(
                                navArgument("id") {
                                    type = NavType.StringType
                                },
                            )
                        ) {
                            val id = it.arguments?.getString("id")!!
                            WeatherItemScreen(
                                id = id,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }

        }
    }
}