package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserAtcSession
import kotlinx.serialization.decodeFromString

/**
 * Retrieves an ATC session from the log of a given user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-session)
 *
 * @param userId ID of the User
 * @param atcSessionId ID of the ATC Session
 */
class GetUserAtcSession(userId: String, atcSessionId: String) :
    Get<UserAtcSession>("users/$userId/atc/$atcSessionId") {

    override fun deserialize(data: String): UserAtcSession = jsonDefault.decodeFromString(data)

}
