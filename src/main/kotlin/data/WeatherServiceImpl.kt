package data

import data.dto.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*
import kotlinx.serialization.*;
import kotlinx.serialization.json.Json;


class WeatherServiceImpl(private val client: HttpClient, private val attributes: Attributes) : WeatherService {

    override suspend fun getNotification(): List<Notification> {
        return listOf(
            Notification("warning", "This is test content1", "2023/10/19"),
            Notification("warning", "This is test content2", "2023/10/19"),
            Notification("warning", "This is test content3", "2023/10/19"),
            Notification("warning", "This is test content4", "2023/10/19"),
            Notification("warning", "This is test content5", "2023/10/19"),
            Notification("warning", "This is test content6", "2023/10/19"))
    }

    override suspend fun getLocation(): LocationFromIP =
        tryToExecute<LocationFromIP>(APIS.LOCATION_API) { get("check") }

    override suspend fun searchWeatherByCityName(city: String): List<SearchItem> =
        tryToExecute<List<SearchItem>>(APIS.WEATHER_API) { get("search.json") { parameter("q", city) } }

    private suspend inline fun <reified T> tryToExecute(
        api: APIS,
        method: HttpClient.() -> HttpResponse
    ): T {
        try {
            attributes.put(AttributeKey("API"), api.value)
            val response = client.method()
            return response.body<T>()
        } catch (e: Throwable) {
            throw e
        }
    }
}

