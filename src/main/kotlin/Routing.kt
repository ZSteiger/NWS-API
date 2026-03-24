package com.nwsapi.com

import com.nwsapi.model.WeatherForecast
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

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
            
            call.respondText(
                "Forecast: ${forecast.shortForecast}\nTemperature: ${forecast.temperature}°F (${forecast.getTemperatureCharacterization()})"
            )
        }
    }
}
