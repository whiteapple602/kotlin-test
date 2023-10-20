package data

import data.dto.LocationFromIP
import data.dto.Notification
import data.dto.SearchItem

interface WeatherService {

    suspend fun getNotification(): List<Notification>

    suspend fun searchWeatherByCityName(city: String): List<SearchItem>

    suspend fun getLocation(): LocationFromIP
}