package com.avonfitzgerald.infinitelive.endpoint.flight.model

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-route#positionreport)
 *
 * @property latitude Decimal latitude of the aircraft at this position.
 * @property longitude Decimal longitude of the aircraft at this position.
 * @property altitude Altitude of the aircraft in feet.
 * @property track Track / Course of aircraft in degrees.
 * @property groundSpeed Ground speed of the aircraft in knots.
 * @property date Position report time of the flight in the following format: YYYY-MM-DD HH:mm:ssZ
 */
@kotlinx.serialization.Serializable
data class PositionReport(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val track: Double,
    val groundSpeed: Double,
    val date: String,
)
