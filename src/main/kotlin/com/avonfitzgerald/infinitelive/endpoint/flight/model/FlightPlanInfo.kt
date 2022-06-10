package com.avonfitzgerald.infinitelive.endpoint.flight.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/flight-plan#flightplaninfo)
 *
 * @property flightPlanId Unique identifier for the flight plan.
 *
 * @property flightId Unique identifier for the flight. Associate with the response from the Get Flights endpoint.
 *
 * @property lastUpdate Last report time of the flight plan in the following format: YYYY-MM-DD HH:mm:ssZ
 *
 * @property flightPlanItems An array of [FlightPlanItem] which contain waypoint and procedure data
 * for points in a flight plan.
 *
 * @property flightPlanType Type of flight plan (IFR/VFR)
 */
@Serializable
data class FlightPlanInfo(
    val flightPlanId: String,
    val flightId: String,
    val lastUpdate: String,
    val flightPlanItems: List<FlightPlanItem>,
    val flightPlanType: FlightPlanType
)
