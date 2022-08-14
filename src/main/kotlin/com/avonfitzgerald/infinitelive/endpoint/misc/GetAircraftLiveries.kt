package com.avonfitzgerald.infinitelive.endpoint.misc

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.misc.model.LiveryData
import kotlinx.serialization.decodeFromString

/**
 * Retrieve a list of all liveries for an aircraft model.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/aircraft-liveries)
 */
class GetAircraftLiveries(aircraftId: String) : Get<List<LiveryData>>("aircraft/$aircraftId/liveries") {
    override fun deserialize(data: String): List<LiveryData> = jsonDefault.decodeFromString(data)
}