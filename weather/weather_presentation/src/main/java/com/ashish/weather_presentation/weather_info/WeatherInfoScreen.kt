package com.ashish.weather_presentation.weather_info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ashish.core.util.toDateString
import com.ashish.core_ui.DarkGray
import com.ashish.core_ui.LocalSpacing
import com.ashish.core_ui.TextWhite
import com.ashish.core_ui.TopBarColor
import com.ashish.weather_domain.model.WeatherData
import com.google.accompanist.pager.ExperimentalPagerApi

/***
 * Shows the weather info, when user taps the item from the weather lists
 */

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WeatherItemScreen(
    id: String,
    viewModel: WeatherInfoViewModel = hiltViewModel(),
    navController: NavController,
) {
    val spacing = LocalSpacing.current
    val weatherData = viewModel.state.weatherData

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Weather",
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    color = TextWhite
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = TopBarColor
            ),
            navigationIcon = {
                IconButton(
                    modifier = Modifier.testTag("test_arrow_back_button"),
                    onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        )
        Row(
            modifier = Modifier.padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = weatherData?.venueName ?: "",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                    Text(
                        text = "${weatherData?.weatherTemperature}\u00B0",
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Text(
                    text = weatherData?.weatherCondition ?: "",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Divider(color = DarkGray, thickness = spacing.dividerThickness)
                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                TableScreen(weatherData = weatherData)
                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Divider(color = DarkGray, thickness = spacing.dividerThickness)

                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                weatherData?.lastUpdated?.toDateString()?.let {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Last Updated: $it",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
    )
}

@Composable
fun TableScreen(weatherData: WeatherData?) {
    // Each cell of a column must have the same weight
    val column1Weight = .3f // 30%
    val column2Weight = .3f // 70%
    val column3Weight = .3f
    LazyColumn {
        // Column Header
        item {
            Row {
                TableCell(text = "Feels like", weight = column1Weight)
                TableCell(text = "Humidity", weight = column2Weight)
                TableCell(text = "Wind", weight = column3Weight)
            }
        }
        // rows
        items(1) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = weatherData?.weatherFeelsLike.toString(), weight = column1Weight)
                TableCell(text = weatherData?.humidity.toString(), weight = column2Weight)
                TableCell(text = weatherData?.wind.toString(), weight = column2Weight)

            }
        }
    }
}
