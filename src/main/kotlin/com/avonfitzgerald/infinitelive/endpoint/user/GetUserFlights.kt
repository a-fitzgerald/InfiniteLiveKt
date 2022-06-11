package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.user.model.PaginatedList
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserFlight
import kotlinx.serialization.decodeFromString

/**
 * Retrieves the online flight logbook for a given user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-flights)
 *
 * @param userId ID of the User
 * @param page Index of the page to retrieve (WARNING: Starts at 1)
 */
class GetUserFlights(userId: String, page: Int = 1) :
    Get<PaginatedList<List<UserFlight>>>("users/$userId/flights?page=$page") {

    override fun deserialize(data: String): PaginatedList<List<UserFlight>> = jsonDefault.decodeFromString(data)

}
