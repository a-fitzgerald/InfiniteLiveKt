package com.avonfitzgerald.infinitelive.endpoint.airport.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = AtcFacilityTypeSerializer::class)
enum class AtcEntityType(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    GROUND(0),
    TOWER(1),
    UNICOM(2),
    CLEARANCE(3),
    APPROACH(4),
    DEPARTURE(5),
    CENTER(6),
    ATIS(7),
    AIRCRAFT(8),
    RECORDED(9),
    UNKNOWN(10),
    UNUSED(11)
}

internal object AtcFacilityTypeSerializer : KSerializer<AtcEntityType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("AtcEntityType", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: AtcEntityType) =
        encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): AtcEntityType = intToAtcFacilityType(decoder.decodeInt())

    private fun intToAtcFacilityType(int: Int) = when (int) {
        AtcEntityType.GROUND.value -> AtcEntityType.GROUND
        AtcEntityType.TOWER.value -> AtcEntityType.TOWER
        AtcEntityType.UNICOM.value -> AtcEntityType.UNICOM
        AtcEntityType.CLEARANCE.value -> AtcEntityType.CLEARANCE
        AtcEntityType.APPROACH.value -> AtcEntityType.APPROACH
        AtcEntityType.DEPARTURE.value -> AtcEntityType.DEPARTURE
        AtcEntityType.CENTER.value -> AtcEntityType.CENTER
        AtcEntityType.ATIS.value -> AtcEntityType.ATIS
        AtcEntityType.AIRCRAFT.value -> AtcEntityType.AIRCRAFT
        AtcEntityType.RECORDED.value -> AtcEntityType.RECORDED
        AtcEntityType.UNKNOWN.value -> AtcEntityType.UNKNOWN
        AtcEntityType.UNUSED.value -> AtcEntityType.UNUSED
        else -> AtcEntityType.INVALID
    }

}
