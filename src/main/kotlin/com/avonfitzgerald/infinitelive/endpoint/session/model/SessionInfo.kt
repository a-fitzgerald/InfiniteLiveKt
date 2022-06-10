package com.avonfitzgerald.infinitelive.endpoint.session.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/sessions#sessioninfo)
 *
 * @property id The unique identifier for the server. Use this to request flights and ATC data
 * @property name Name of the server
 * @property maxUsers Maximum number of users the server can accept
 * @property userCount Connected users to the server
 * @property type Type of server (Unrestricted/Restricted)
 */
@Serializable
data class SessionInfo(
    val id: String,
    val name: String,
    val maxUsers: Int,
    val userCount: Int,
    val type: SessionType
)
