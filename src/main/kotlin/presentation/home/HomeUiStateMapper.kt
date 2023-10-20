package presentation.home

import java.text.SimpleDateFormat
import java.util.*

private fun convertEpochMillisecondsToDate(epochMilliseconds: Int): String {
    val date = Date(epochMilliseconds * 1000L)
    val sdf = SimpleDateFormat("dd MMM", Locale.ENGLISH)
    return sdf.format(date)
}

private fun getDayOfWeek(timestamp: Int): String {
    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(timestamp * 1000)
}


private fun getUvIndexDescription(uvIndex: Double): String {
    return when {
        uvIndex > 5 -> "High"
        uvIndex < 5 -> "Low"
        else -> "Medium"
    }
}