package com.avonfitzgerald.infinitelive.endpoint.common

import com.avonfitzgerald.infinitelive.endpoint.airport.model.AtcEntityType
import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-session#atcfacility)
 *
 * @property id ID of the ATC Facility.
 * @property airportIcao The ICAO of the airport at which the facility is located.
 * @property latitude Latitude of the ATC Facility.
 * @property longitude Longitude of the ATC Facility.
 * @property frequencyType Type of ATC Facility.
 */
@Serializable
data class AtcFacility(
    val id: String,
    val airportIcao: String?,
    val latitude: Double,
    val longitude: Double,
    val frequencyType: AtcEntityType
)
