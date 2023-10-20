package data

import data.dto.Notification

interface WeatherService {

    suspend fun getNotification(): List<Notification>

}