package com.avonfitzgerald.infinitelive.endpoint.misc

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.misc.model.LiveryData
import kotlinx.serialization.decodeFromString

/**
 * Retrieve a list of all aircraft liveries.
 *
 * [Official Documentation](https://api.infiniteflight.com/public/v2/aircraft/liveries)
 */
class GetAllLiveries : Get<List<LiveryData>>("aircraft/liveries") {
    override fun deserialize(data: String): List<LiveryData> = jsonDefault.decodeFromString(data)
}