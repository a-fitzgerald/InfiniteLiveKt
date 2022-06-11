package com.avonfitzgerald.infinitelive.endpoint.airport

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.airport.model.AirportStatus
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import kotlinx.serialization.decodeFromString

/**
 * Retrieve active ATC status information for an airport, and the number of inbound and outbound aircraft.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/airport-status)
 *
 * @param sessionId Session (Server) ID of the Live Server
 * @param airportIcao ICAO of the airport to get the status for
 */
class GetAirportStatus(sessionId: String, airportIcao: String) :
    Get<AirportStatus>("sessions/$sessionId/airport/$airportIcao/status") {

    override fun deserialize(data: String): AirportStatus = jsonDefault.decodeFromString(data)

}
