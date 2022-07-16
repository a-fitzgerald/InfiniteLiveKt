package com.avonfitzgerald.infinitelive.endpoint.user

import com.avonfitzgerald.infinitelive.core.Get
import com.avonfitzgerald.infinitelive.endpoint.common.jsonDefault
import com.avonfitzgerald.infinitelive.endpoint.user.model.GradeInfo
import kotlinx.serialization.decodeFromString

/**
 * Retrieve the full grade table and detailed statistics for a user.
 *
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade)
 *
 * @param userId ID of the User
 */
class GetUserGrade(userId: String) : Get<GradeInfo>("users/$userId") {
    override fun deserialize(data: String): GradeInfo = jsonDefault.decodeFromString(data)
}
