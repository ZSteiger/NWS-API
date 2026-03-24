package com.nwsapi.model

import kotlinx.serialization.Serializable

@Serializable
data class GridPointResponse(
    val properties: GridPointProperties? = null,
    val status: Int? = null
)

@Serializable
data class GridPointProperties(
    val forecast: String,
    val relativeLocation: RelativeLocation
)

@Serializable
data class RelativeLocation(
    val properties: RelativeLocationProperties
)

@Serializable
data class RelativeLocationProperties(
    val city: String,
    val state: String
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
    val shortForecast: String? = null,
    val temperature: Int? = 0,
    val city: String? = null,
    val state: String? = null,
    val error: String? = null
) {
    fun getTemperatureCharacterization(): String {
        if (temperature == null) return ""
        return when {
            temperature >= 80 -> "hot"
            temperature <= 40 -> "cold"
            else -> "moderate"
        }
    }
}