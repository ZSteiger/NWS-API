package com.nwsapi.model

import kotlinx.serialization.Serializable

@Serializable
data class GridPointResponse(
    val properties: GridPointProperties
)


@Serializable
data class GridPointProperties(
    val forecast: String
)

@Serializable
data class ForecastResponse(
    val properties: ForecastProperties
)

@Serializable
data class ForecastProperties(
    val periods: List<ForecastPeriod>
)

@Serializable
data class ForecastPeriod(
    val shortForecast: String,
    val temperature: Int
)

@Serializable
data class WeatherForecast(
    val shortForecast: String,
    val temperature: Int
) {
    fun getTemperatureCharacterization(): String {
        return when {
            temperature >= 80 -> "hot"
            temperature <= 40 -> "cold"
            else -> "moderate"
        }
    }
}