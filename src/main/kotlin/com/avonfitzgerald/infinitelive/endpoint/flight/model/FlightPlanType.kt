package com.avonfitzgerald.infinitelive.endpoint.flight.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = FlightPlanTypeSerializer::class)
enum class FlightPlanType(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    VFR(0),
    IFR(1)
}

private class FlightPlanTypeSerializer : KSerializer<FlightPlanType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FlightPlanType", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: FlightPlanType) =
        encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): FlightPlanType =
        intToFlightPlanType(decoder.decodeInt())

    private fun intToFlightPlanType(int: Int) = when (int) {
        FlightPlanType.VFR.value -> FlightPlanType.VFR
        FlightPlanType.IFR.value -> FlightPlanType.IFR
        else -> FlightPlanType.INVALID
    }


}