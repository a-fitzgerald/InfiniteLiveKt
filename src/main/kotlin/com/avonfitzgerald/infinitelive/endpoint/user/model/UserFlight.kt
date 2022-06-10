package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-flights#userflight)
 *
 * @property id The ID of the flight.
 * @property created The time the flight was created.
 * @property userId The ID of the user who flew the flight.
 * @property aircraftId The ID of the aircraft flown.
 * @property liveryId The ID of the livery flown.
 * @property callsign The callsign of the user during this flight.
 * @property server The name of the server the flight was flown on.
 * @property dayTime The flight time during the day, in minutes.
 * @property nightTime The flight time during the night, in minutes.
 * @property totalTime The total flight time of the flight, in minutes.
 * @property landingCount The number of landings conducted during the flight.
 * @property originAirport The ICAO code of the departure airport.
 * @property destinationAirport The ICAO code of the arrival airport.
 * @property xp The number of XP earned during the flight.
 */
@Serializable
data class UserFlight(
    val id: String,
    val created: String,
    val userId: String,
    val aircraftId: String,
    val liveryId: String?,
    val callsign: String?,
    val server: String?,
    val dayTime: Float,
    val nightTime: Float,
    val totalTime: Float,
    val landingCount: Int,
    val originAirport: String?,
    val destinationAirport: String?,
    val xp: Int
)
