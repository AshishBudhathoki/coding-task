package com.ashish.codingtask

import WeatherListingsScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.SavedStateHandle
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
import com.ashish.weather_domain.usecase.GetWeatherItemUseCase
import com.ashish.weather_presentation.weather_info.WeatherInfoViewModel
import com.ashish.weather_presentation.weather_info.WeatherItemScreen
import com.ashish.weather_presentation.weather_listings.WeatherListViewModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalComposeUiApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class WeatherE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: WeatherRepositoryFake
    private lateinit var getWeatherDataUseCase: GetWeatherDataUseCase
    private lateinit var getWeatherItemUseCase: GetWeatherItemUseCase
    private lateinit var weatherListViewModel: WeatherListViewModel
    private lateinit var weatherInfoViewModel: WeatherInfoViewModel

    private val savedStateHandle = SavedStateHandle().apply {
        set("id", "0")
    }

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        repositoryFake = WeatherRepositoryFake()
        getWeatherDataUseCase = GetWeatherDataUseCase(repositoryFake)
        getWeatherItemUseCase = GetWeatherItemUseCase(repositoryFake)
        weatherListViewModel = WeatherListViewModel(getWeatherDataUseCase)
        weatherInfoViewModel = WeatherInfoViewModel(getWeatherItemUseCase, savedStateHandle)

        composeRule.setContent {
            WeatherListingsTheme {
                navController = rememberNavController()
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
                                },
                                viewModel = weatherListViewModel
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
                                navController = navController,
                                viewModel = weatherInfoViewModel
                            )
                        }
                    }
                }
            }

        }

    }

    @Test
    fun weatherAppEndToEndTest() {
        composeRule
            .onNodeWithText("A-Z")
            .assertIsDisplayed()
            .assertIsSelectable()

        composeRule
            .onNodeWithText("A-Z")
            .performClick()

        assertThat(
            weatherListViewModel.state.weatherData == repositoryFake.weatherDataList.sortedBy {
                it.venueName
            }
        ).isTrue()

        composeRule
            .onNodeWithText("Temperature")
            .assertIsDisplayed()
            .assertIsSelectable()

        composeRule
            .onNodeWithText("Temperature")
            .performClick()

        assertThat(
            weatherListViewModel.state.weatherData == repositoryFake.weatherDataList.sortedBy {
                it.weatherTemperature?.toLong()
            }
        ).isTrue()

        composeRule
            .onNodeWithText("Last Updated")
            .assertIsDisplayed()
            .assertIsSelectable()

        composeRule
            .onNodeWithText("Last Updated")
            .performClick()

        assertThat(
            weatherListViewModel.state.weatherData == repositoryFake.weatherDataList.sortedBy {
                it.lastUpdated
            }
        ).isTrue()


        composeRule.onNodeWithTag("test_lazy_column")
            .assert(hasScrollAction())

        composeRule.onNodeWithTag("test_lazy_column")
            .onChildAt(0)
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.WEATHER_ITEM)
        ).isTrue()

        composeRule
            .onNodeWithText("Feels like")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Humidity")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Wind")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Last Updated:", true)
            .assertIsDisplayed()

        composeRule.onNodeWithTag("test_arrow_back_button")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.WEATHER_HOME)
        ).isTrue()
    }
}