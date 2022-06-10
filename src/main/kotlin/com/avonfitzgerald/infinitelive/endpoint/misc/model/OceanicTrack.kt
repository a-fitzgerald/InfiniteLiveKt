package com.avonfitzgerald.infinitelive.endpoint.misc.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/oceanic-tracks#oceanictrack)
 *
 * @property name Name of the track. This is normally represented by letters.
 *
 * @property path You can correlate these with data
 * from the [Airport Editing Project](https://github.com/infiniteflightairportediting/).
 *
 * @property eastLevels An array of Flight Level altitudes that aircraft can fly eastbound on using this track.
 *
 * @property westLevels An array of Flight Level altitudes that aircraft can fly westbound on using this track.
 *
 * @property type Type of Oceanic Track. Infinite Flight supports North Atlantic Tracks
 * in addition to custom tracks defined for events.
 *
 * @property lastSeen Last date and time at which the Oceanic Tracks were updated,
 * in the following format: YYYY-MM-DDTHH:mm:ssZ
 */
@Serializable
data class OceanicTrack(
    val name: String,
    val path: List<String>,
    val eastLevels: List<Int>?,
    val westLevels: List<Int>?,
    val type: String,
    val lastSeen: String
)
