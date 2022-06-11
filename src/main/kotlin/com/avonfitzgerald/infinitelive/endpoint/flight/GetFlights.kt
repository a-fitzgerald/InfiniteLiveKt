package com.avonfitzgerald.infinitelive.endpoint.flight

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.flight.model.FlightEntry
import kotlinx.serialization.decodeFromString

/**
 * Retrieve a list of all flights for a session.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flights)
 *
 * @param sessionId ID of the session returned from the Sessions endpoint.
 */
class GetFlights(sessionId: String) : Get<List<FlightEntry>>("sessions/$sessionId/flights") {
    override fun deserialize(data: String): List<FlightEntry> = jsonDefault.decodeFromString(data)
}
