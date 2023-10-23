package presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import data.dto.Notification
import presentation.theme.maxTemperatureSpanStyle
import presentation.theme.minTemperatureSpanStyle
import presentation.home.DayForecastUiState

@Composable
fun DayForecast(
    state: Notification,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Text(
            text = state.content,
            style = MaterialTheme.typography.body1.copy(),
            modifier = Modifier.padding(end = 48.dp).width(148.dp),
        )

        Text(
            text = state.date,
            maxLines = 1,
            style = MaterialTheme.typography.body2,
        )

    }
}