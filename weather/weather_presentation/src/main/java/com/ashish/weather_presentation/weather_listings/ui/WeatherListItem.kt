package com.ashish.weather_presentation.weather_listings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.ashish.core_ui.LocalSpacing
import com.ashish.weather_domain.model.WeatherData

@Composable
fun WeatherListItem(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    selectedWeatherWeather: (WeatherData) -> Unit
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier.clickable { selectedWeatherWeather(weatherData) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = weatherData.venueName,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(
                    text = "${weatherData.weatherTemperature}\u00B0",
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
            Text(
                text = weatherData.weatherCondition ?: "",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
