package com.avonfitzgerald.infinitelive.endpoint.flight.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


@Serializable(with = FlightPlanItemTypeSerializer::class)
enum class FlightPlanItemType(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    SID(0),
    STAR(1),
    APPROACH(2),
    TRACK(3),
    UNKNOWN(5)
}

private object FlightPlanItemTypeSerializer : KSerializer<FlightPlanItemType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FlightPlanItemType", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: FlightPlanItemType) =
        encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): FlightPlanItemType = intToFlightPlanItemType(decoder.decodeInt())

    private fun intToFlightPlanItemType(int: Int) = when (int) {
        FlightPlanItemType.INVALID.value -> FlightPlanItemType.INVALID
        FlightPlanItemType.SID.value -> FlightPlanItemType.SID
        FlightPlanItemType.STAR.value -> FlightPlanItemType.STAR
        FlightPlanItemType.APPROACH.value -> FlightPlanItemType.APPROACH
        FlightPlanItemType.TRACK.value -> FlightPlanItemType.TRACK
        FlightPlanItemType.UNKNOWN.value -> FlightPlanItemType.UNKNOWN
        else -> FlightPlanItemType.INVALID
    }

}
