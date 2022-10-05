import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashish.core_ui.*
import com.ashish.weather_presentation.weather_listings.WeatherListViewModel
import com.ashish.weather_presentation.weather_listings.WeatherListingEvent
import com.ashish.weather_presentation.weather_listings.WeatherListingsState
import com.ashish.weather_presentation.weather_listings.ui.CustomScrollableTabRow
import com.ashish.weather_presentation.weather_listings.ui.WeatherListItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherListingsScreen(
    viewModel: WeatherListViewModel = hiltViewModel(),
    onNavigateToSearch: (String) -> Unit,
) {
    val spacing = LocalSpacing.current

    val tabs = listOf("A-Z", "Temperature", "Last Updated")

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )
    val state = viewModel.state
    var selectedTabIndex by remember { mutableStateOf(state.tabPosition) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            TopAppBarLayout()

            CustomScrollableTabRow(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
            ) { tabIndex ->
                selectedTabIndex = tabIndex
                when (tabIndex) {
                    0 -> {
                        viewModel.onEvent(WeatherListingEvent.AlphabetSort)
                    }
                    1 -> {
                        viewModel.onEvent(WeatherListingEvent.TemperatureSort)
                    }
                    2 -> {
                        viewModel.onEvent(WeatherListingEvent.LastUpdatedSort)
                    }
                }

            }

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(WeatherListingEvent.Refresh)
                }
            ) {
                WeatherListLazyColumn(state, spacing, onNavigateToSearch)

            }
        }

        FilterButton()
    }
}

@Composable
private fun FilterButton() {
    Button(modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = TextWhite),
        onClick = { println("hi") }) {
        Text(
            color = DarkGray,
            text = "Filter"
        )
    }
}

@Composable
private fun WeatherListLazyColumn(
    state: WeatherListingsState,
    spacing: Dimensions,
    onNavigateToSearch: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("test_lazy_column")

    ) {
        items(state.weatherData.size) { i ->
            val weatherData = state.weatherData[i]
            WeatherListItem(
                weatherData = weatherData,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium)
            ) {
                onNavigateToSearch(it.id.toString())
            }
            if (i < state.weatherData.size) {
                Divider(
                    modifier = Modifier.padding(
                        horizontal = spacing.spaceMedium
                    )
                )
            }
        }
    }
}

@Composable
private fun TopAppBarLayout() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Weather",
                textAlign = TextAlign.Center,
                color = TextWhite
            )
        },
        backgroundColor = TopBarColor
    )
}
