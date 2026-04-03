package com.nwsapi.com

import com.nwsapi.model.WeatherForecast
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import kotlinx.serialization.json.internal.readJson

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        
        get("/weather") {
            val lat = call.parameters["lat"]?.toDoubleOrNull()
            val lon = call.parameters["lon"]?.toDoubleOrNull()
            
            if (lat == null || lon == null) {
                call.respondText("Missing required parameters: lat and lon", status = io.ktor.http.HttpStatusCode(400, "Bad Request"))
                return@get
            }
            
            val weatherClient = WeatherClient()
            val forecast = weatherClient.getForecast(lat, lon)

            if (forecast.error != null) {
                call.respondText(forecast.error)
                return@get
            }

            call.respondText(
                """{
                  "city": "${forecast.city}",
                  "state": "${forecast.state}",
                  "forecast": "${forecast.shortForecast}",
                  "temperature": "${forecast.temperature}°F (${forecast.getTemperatureCharacterization()})"
                }""",
                contentType = ContentType.Application.Json
            )
        }
    }
}
