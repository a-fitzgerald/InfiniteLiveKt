package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Post
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserStats
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true // Ignoring "groups" deprecated property
    coerceInputValues = true
}

/**
 * Retrieve user statistics for multiple users, including their grade, flight time and username.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-stats)
 *
 * @param userIds An array of user ID strings retrieved from another endpoint
 * @param discourseNames An array of IFC Usernames. Not case-sensitive.
 * @param userHashes An array of user hashes retrieved in-app or from another endpoint. All letters must be upper case.
 *
 */
class PostUserStats(
    userIds: List<String> = emptyList(),
    discourseNames: List<String> = emptyList(),
    userHashes: List<String> = emptyList()
) : Post<List<UserStats>>(
    "users",
    Json.encodeToString(UserStatsBody.serializer(), UserStatsBody(userIds, discourseNames, userHashes))
) {

    override fun deserialize(data: String): List<UserStats> = json.decodeFromString(data)

    @Serializable
    private data class UserStatsBody(
        val userIds: List<String> = emptyList(),
        val discourseNames: List<String> = emptyList(),
        val userHashes: List<String> = emptyList()
    )

}
