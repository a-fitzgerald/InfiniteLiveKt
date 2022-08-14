package com.avonfitzgerald.infinitelive.endpoint.misc.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/liveries#liverydata)
 *
 * @property id Unique identifier for the livery
 * @property aircraftID ID of the aircraft model
 * @property aircraftName Name of the aircraft model
 * @property liveryName Name of the livery
 */
@Serializable
data class LiveryData(val id: String, val aircraftID: String, val aircraftName: String, val liveryName: String)