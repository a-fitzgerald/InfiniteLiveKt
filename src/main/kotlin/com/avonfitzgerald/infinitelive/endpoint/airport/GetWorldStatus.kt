package com.avonfitzgerald.infinitelive.endpoint.airport

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.airport.model.AirportStatus
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Retrieve active ATC status information and inbound/outbound aircraft information for
 * all airports with activity on a specific server.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/world-status)
 *
 * @param sessionId Session (Server) ID of the Live Server
 */
class GetWorldStatus(sessionId: String) : Get<List<AirportStatus>>("sessions/$sessionId/world") {

    override fun deserialize(data: String): List<AirportStatus> =
        Json.decodeFromString(data)

}
