package com.avonfitzgerald.infinitelive.endpoint.flight.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-plan#coordinate)
 *
 * @property latitude Current decimal latitude of the aircraft
 * @property longitude Current decimal longitude of the aircraft
 * @property altitude TODO
 */
@Serializable
data class Coordinate(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double
)
