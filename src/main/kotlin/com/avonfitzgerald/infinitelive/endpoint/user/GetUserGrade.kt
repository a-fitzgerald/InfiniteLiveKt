package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.user.model.GradeInfo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true // Ignoring "groups" deprecated property
    coerceInputValues = true
}

/**
 * Retrieve the full grade table and detailed statistics for a user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade)
 *
 * @param userId ID of the User
 */
class GetUserGrade(userId: String) : Get<GradeInfo>("users/$userId") {
    override fun deserialize(data: String): GradeInfo = json.decodeFromString(data)
}
