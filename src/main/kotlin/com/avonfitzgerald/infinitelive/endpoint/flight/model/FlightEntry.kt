package com.avonfitzgerald.infinitelive.endpoint.flight.model

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flights#flightentry)
 *
 * @property flightId Unique identifier for the flight.
 * @property userId Unique identifier for the user.
 * @property aircraftId Unique identifier for the aircraft type.
 * @property liveryId Unique identifier for the livery and aircraft combination.
 * @property username The user's forum username if the account is linked. If the account isn't linked, this will be null.
 * @property virtualOrganization The virtual organization of the user's forum account if linked. Can be null if not set.
 * @property callsign Callsign for the flight.
 * @property latitude Current decimal latitude of the aircraft.
 * @property longitude Current decimal longitude of the aircraft.
 * @property altitude Current altitude of the aircraft in feet.
 * @property speed Current groundspeed of the aircraft in knots.
 * @property verticalSpeed Current vertical speed of the aircraft in ft/min.
 * @property track Track of the aircraft in degrees.
 * @property heading Heading of the aircraft in degrees.
 * @property lastReport Last position report time of the flight in the following format: YYYY-MM-DD HH:mm:ssZ
 */
@kotlinx.serialization.Serializable
data class FlightEntry(
    val flightId: String,
    val userId: String,
    val aircraftId: String,
    val liveryId: String,
    val username: String?,
    val virtualOrganization: String?,
    val callsign: String,
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val speed: Double,
    val verticalSpeed: Double,
    val track: Double,
    val heading: Float,
    val lastReport: String
)
