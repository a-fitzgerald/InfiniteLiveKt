package com.avonfitzgerald.infinitelive.endpoint.flight

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.flight.model.FlightPlanInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json {
    // Ignoring "waypoints" deprecated property
    ignoreUnknownKeys = true
}

/**
 * Retrieve the flight plan for a specific active flight.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-plan)
 *
 * @param sessionId ID of the session returned from the Sessions endpoint.
 * @param flightId ID of the flight. The flight must be in an active session and have a filed flight plan.
 */
class GetFlightPlan(sessionId: String, flightId: String) :
    Get<FlightPlanInfo>("sessions/$sessionId/flights/$flightId/flightplan") {

    override fun deserialize(data: String): FlightPlanInfo = json.decodeFromString(data)

}
