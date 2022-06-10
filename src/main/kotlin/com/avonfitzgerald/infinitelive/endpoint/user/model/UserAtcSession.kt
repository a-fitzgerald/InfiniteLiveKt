package com.avonfitzgerald.infinitelive.endpoint.user.model

import com.avonfitzgerald.infinitelive.endpoint.common.AtcFacility

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-sessions#useratcsession)
 *
 * @property id The ID of the session.
 *
 * @property atcSessionGroupId Identifies a group of sessions
 * (for when a controller opens multiple frequencies at the same airport).
 *
 * @property facility Details of the facility that was opened.
 *
 * @property created The time at which the frequency was first opened.
 *
 * @property updated The time at which last report was received.
 *
 * @property operations The number of operations earned during the session.
 *
 * @property totalTime The duration of the session in minutes.
 */
@kotlinx.serialization.Serializable
data class UserAtcSession(
    val id: String,
    val atcSessionGroupId: String,
    val facility: AtcFacility,
    val created: String,
    val updated: String,
    val operations: Int?,
    val totalTime: Double
)