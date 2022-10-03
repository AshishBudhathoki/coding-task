package com.ashish.codingtask

import WeatherListingsScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ashish.codingtask.navigation.Route
import com.ashish.codingtask.repository.WeatherRepositoryFake
import com.ashish.codingtask.theme.WeatherListingsTheme
import com.ashish.weather_domain.usecase.GetWeatherDataUseCase
import com.ashish.weather_presentation.weather_info.WeatherItemScreen
import com.ashish.weather_presentation.weather_listings.WeatherListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class WeatherE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: WeatherRepositoryFake
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase
    private lateinit var weatherListViewModel: WeatherListViewModel

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        repositoryFake = WeatherRepositoryFake()
        getWeatherDataUseCase = GetWeatherDataUseCase(repositoryFake)
        weatherListViewModel = WeatherListViewModel(getWeatherDataUseCase)

        composeRule.setContent {
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

    @Test
    fun givenUser_whenOpensApp_TabsDisplayed(){
        composeRule
            .onNodeWithText("A-Z")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Temperature")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Last Updated")
            .assertIsDisplayed()
    }
}