package presentation.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import presentation.base.BaseScreen
import presentation.composables.*


object HomeScreen : BaseScreen<HomeScreenModel, HomeUiEffect, HomeUIState, HomeInteractionListener>() {


    override fun onEffect(effect: HomeUiEffect, navigator: Navigator) {

    }

    @Composable
    override fun OnRender(state: HomeUIState, listener: HomeInteractionListener) {
        val viewModel = getScreenModel<HomeScreenModel>()
        val windowState = rememberWindowState()

        HomeScreenContent(
            windowState = windowState,
            listener = viewModel,
            state = state
        )
    }

    @Composable
    override fun Content() {
        Init(getScreenModel<HomeScreenModel>())
    }

    @Composable
    fun HomeScreenContent(
        modifier: Modifier = Modifier,
        windowState: WindowState,
        state: HomeUIState,
        listener: HomeInteractionListener,
    ) {

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingAnimation()
            }
        }
        else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .width(windowState.size.width)
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    BlurredCard(
                        modifier = Modifier.weight(1.5f),
                        blurBackground = {
                            if (state.weatherUiState.daysForecastUiState.isNotEmpty()) {
                                WeatherImageLoader(
                                    url = state.weatherUiState.daysForecastUiState[0].iconUrl,
                                    modifier = Modifier.size(300.dp)
                                        .blur(80.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                                        .alpha(0.5f),
                                )
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.height(344.dp).width(374.dp)
                        ) {
                            Text(
                                text = "Notifications",
                                style = MaterialTheme.typography.h2,
                                modifier = Modifier.padding(16.dp)
                            )

                            val scrollState = rememberLazyListState()
                            val coroutineScope = rememberCoroutineScope()
                            LazyColumn(
                                state = scrollState,
                                verticalArrangement = Arrangement.spacedBy(20.dp),
                                modifier = Modifier
                                    .widthIn(390.dp)
                                    .draggable(
                                        orientation = Orientation.Vertical,
                                        state = rememberDraggableState { delta ->
                                            coroutineScope.launch {
                                                scrollState.scrollBy(-delta)
                                            }
                                        }),
                                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp)
                            ) {
                                items(state.notificationUIState) { data ->
                                    DayForecast(state = data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
