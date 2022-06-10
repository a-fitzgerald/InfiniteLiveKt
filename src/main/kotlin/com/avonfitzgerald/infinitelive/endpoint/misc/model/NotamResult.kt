package com.avonfitzgerald.infinitelive.endpoint.misc.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/notams#notamresult)
 *
 * @property id Unique identifier for the NOTAM.
 * @property title Short title for NOTAM.
 * @property author Name of NOTAM author.
 * @property type Type of NOTAM.
 * @property sessionId The ID of the session on which the NOTAM is published. null for all sessions.
 * @property radius The radius of the radius in NM.
 * @property message Main message for NOTAM.
 * @property latitude Decimal latitude of the center of the NOTAM.
 * @property longitude Decimal longitude of the center of the NOTAM.
 * @property icao ICAO of the nearest airport to the NOTAM.
 * @property floor Lowest altitude of NOTAM in feet.
 * @property ceiling Highest altitude of NOTAM in feet.
 * @property startTime Time at which the NOTAM comes into effect.
 * @property endTime Time at which the NOTAM expires.
 */
@Serializable
data class NotamResult(
    val id: String,
    val title: String,
    val author: String,
    val type: NotamType,
    val sessionId: String,
    val radius: Float,
    val message: String,
    val latitude: Double,
    val longitude: Double,
    val icao: String,
    val floor: Int,
    val ceiling: Int,
    val startTime: String,
    val endTime: String
)
