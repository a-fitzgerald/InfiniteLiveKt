package com.avonfitzgerald.infinitelive.endpoint.airport.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/airport-status#activeatcfacility)
 *
 * @property frequencyId Unique identifier for the open frequency.
 * @property userId Unique identifier for the user controlling the frequency.
 * @property username The user's forum username if the account is linked. If the account isn't linked, this will be null
 * @property virtualOrganization (not currently in use)
 * @property airportName The 4-character ICAO identifier for the airport.
 * @property type The type of frequency opened - not all of these are in use.
 * @property latitude Decimal latitude of the airport.
 * @property longitude Decimal longitude of the airport.
 * @property startTime Time at which the frequency was opened, in the following format: YYYY-MM-DD HH:mm:ssZ
 */
@Serializable
data class ActiveAtcFacility(
    val frequencyId: String,
    val userId: String,
    val username: String?,
    val virtualOrganization: String?,
    val airportName: String?,
    val type: AtcEntityType,
    val latitude: Float,
    val longitude: Float,
    val startTime: String
)