package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.user.model.PaginatedList
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserAtcSession
import kotlinx.serialization.decodeFromString

/**
 * Retrieves the ATC session log for a given user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-sessions)
 *
 * @param userId ID of the User
 * @param page Index of the page to retrieve (WARNING: Starts at 1)
 */
class GetUserAtcSessions(userId: String, page: Int = 1) :
    Get<PaginatedList<List<UserAtcSession>>>("users/$userId/atc?page=$page") {

    override fun deserialize(data: String): PaginatedList<List<UserAtcSession>> = jsonDefault.decodeFromString(data)

}
