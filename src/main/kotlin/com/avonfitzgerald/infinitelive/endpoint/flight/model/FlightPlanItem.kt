package com.avonfitzgerald.infinitelive.endpoint.flight.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-plan#flightplanitem)
 *
 * @property name Name of the waypoint or the procedure.
 * In the children array, this is the name of a waypoint inside a procedure.
 *
 * @property type Type of procedure for this item.
 * Only use this if the [FlightPlanItem]'s children field is populated and not null.
 *
 * @property children An array of [FlightPlanItem] containing waypoint information about a procedure.
 * Only present if this item defines a procedure (SID/STAR/Approach/Track).
 * If not, assume this is a Fix/VOR/Custom User Waypoint.
 *
 * @property identifier Identifier for the waypoint or the procedure. This is not unique.
 *
 * @property altitude The altitude in feet for this waypoint.
 * This is optionally defined by the user and defaults to -1 if not set.
 *
 * @property location A [Coordinate] object defining the position of this waypoint.
 */
@Serializable
data class FlightPlanItem(
    val name: String?,
    val type: FlightPlanItemType = FlightPlanItemType.INVALID,
    val children: List<FlightPlanItem>?,
    val identifier: String?,
    val altitude: Int,
    val location: Coordinate
)
