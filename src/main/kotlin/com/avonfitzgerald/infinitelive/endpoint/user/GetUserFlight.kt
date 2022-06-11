package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserFlight
import kotlinx.serialization.decodeFromString

/**
 * Retrieves a flight from the logbook of a given user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-flight)
 *
 * @param userId ID of the User
 * @param flightId ID of the Flight
 */
class GetUserFlight(userId: String, flightId: String) :
    Get<UserFlight>("users/$userId/flights/$flightId") {

    override fun deserialize(data: String): UserFlight = jsonDefault.decodeFromString(data)

}
