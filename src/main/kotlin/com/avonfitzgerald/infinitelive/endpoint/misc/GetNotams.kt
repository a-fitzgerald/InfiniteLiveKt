package com.avonfitzgerald.infinitelive.endpoint.misc

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.misc.model.NotamResult
import kotlinx.serialization.decodeFromString

/**
 * Retrieve a list of all NOTAMs for a session.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/notams)
 *
 * @param sessionId ID of the session returned from the Sessions endpoint
 */
class GetNotams(sessionId: String) : Get<List<NotamResult>>("sessions/$sessionId/notams") {
    override fun deserialize(data: String): List<NotamResult> = jsonDefault.decodeFromString(data)
}
