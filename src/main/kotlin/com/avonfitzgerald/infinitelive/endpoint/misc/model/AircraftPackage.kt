package com.avonfitzgerald.infinitelive.endpoint.misc.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/aircraft#aircraftpacakge)
 *
 * @property id Unique identifier for the model
 * @property name Name of the aircraft
 */
@Serializable
data class AircraftPackage(val id: String, val name: String)