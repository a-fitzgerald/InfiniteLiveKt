package com.avonfitzgerald.infinitelive.endpoint.airport.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/airport-status#airport-status)
 *
 * @property airportIcao ICAO of the airport.
 *
 * @property inboundFlightsCount Number of aircraft inbound to this airport
 * (must have final waypoint in flight plan set as the airport ICAO).
 *
 * @property inboundFlights A list of flight identifiers inbound to this airport.
 * Use this to get flight plans or flight route information.
 *
 * @property outboundFlightsCount Number of aircraft departing this airport
 * (must have first waypoint in flight plan set as the airport ICAO)
 *
 * @property outboundFlights A list of flight identifiers outbound from this airport.
 * Use this to get flight plans or flight route information.
 *
 * @property atcFacilities Array of [ActiveAtcFacility] objects.
 */
@Serializable
data class AirportStatus(
    val airportIcao: String,
    val inboundFlightsCount: Int,
    val inboundFlights: List<String>,
    val outboundFlightsCount: Int,
    val outboundFlights: List<String>,
    val atcFacilities: List<ActiveAtcFacility>
)