package com.nwsapi.com

import com.nwsapi.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class WeatherClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getForecast(lat: Double, lon: Double): WeatherForecast {
        val gridUrl = "https://api.weather.gov/points/$lat,$lon"
        val gridDataRes = client.get(gridUrl) {
            header("User-Agent", "NWS-API/0.0.1")
        }

        val gridData = gridDataRes.body<GridPointResponse>()
        
        val forecastUrl = gridData.properties.forecast
        val forecastDataRes = client.get(forecastUrl) {
            header("User-Agent", "NWS-API/0.0.1")
        }

        val forecastData = forecastDataRes.body<ForecastResponse>()
        
        val todayPeriod = forecastData.properties.periods.firstOrNull() 
            ?: error("No forecast periods available")
        
        return WeatherForecast(
            shortForecast = todayPeriod.shortForecast,
            temperature = todayPeriod.temperature
        )
    }
}

