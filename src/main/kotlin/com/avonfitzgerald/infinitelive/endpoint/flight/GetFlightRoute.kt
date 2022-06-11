package com.avonfitzgerald.infinitelive.endpoint.flight

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.flight.model.PositionReport
import kotlinx.serialization.decodeFromString

/**
 * Retrieve the flown route of a specific flight with position, altitude, speed and
 * track information at different points in time.
 *
 * Please note, this is currently only supported on the Expert Server and Training Server.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-route)
 *
 * @param sessionId ID of the session returned from the Sessions endpoint.
 * @param flightId ID of the flight. The flight must be in an active session.
 */
class GetFlightRoute(sessionId: String, flightId: String) :
    Get<List<PositionReport>>("sessions/$sessionId/flights/$flightId/route") {

    override fun deserialize(data: String): List<PositionReport> = jsonDefault.decodeFromString(data)

}
