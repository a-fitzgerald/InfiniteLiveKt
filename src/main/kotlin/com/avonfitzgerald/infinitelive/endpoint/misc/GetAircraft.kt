package com.avonfitzgerald.infinitelive.endpoint.misc

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.misc.model.AircraftPackage
import kotlinx.serialization.decodeFromString

/**
 * Retrieve a list of all aircraft models.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/aircraft)
 */
class GetAircraft : Get<List<AircraftPackage>>("aircraft") {
    override fun deserialize(data: String): List<AircraftPackage> = jsonDefault.decodeFromString(data)
}