package com.avonfitzgerald.infinitelive.endpoint.airport

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.airport.model.ActiveAtcFacility
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import kotlinx.serialization.decodeFromString

/**
 * Retrieve active Air Traffic Control frequencies for a session.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/atc)
 *
 * @param sessionId ID of the session returned from the Sessions endpoint.
 */
class GetActiveAtcFrequencies(sessionId: String) : Get<List<ActiveAtcFacility>>("sessions/$sessionId/atc") {
    override fun deserialize(data: String): List<ActiveAtcFacility> = jsonDefault.decodeFromString(data)
}