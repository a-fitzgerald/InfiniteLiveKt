package com.avonfitzgerald.infinitelive.endpoint.airport

import com.avonfitzgerald.infinitelive.core.Get

/**
 * Retrieve the ATIS for an airport on a specific server if it is active.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/atis)
 *
 * @param sessionId Session (Server) ID of the Live Server
 * @param airportIcao ICAO of the airport to get the ATIS for
 */
class GetAirportAtis(sessionId: String, airportIcao: String) :
    Get<String>("sessions/$sessionId/airport/$airportIcao/atis") {

    override fun deserialize(data: String): String = data

}
