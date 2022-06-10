package com.avonfitzgerald.infinitelive.endpoint.misc.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = NotamTypeSerializer::class)
enum class NotamType(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    NOTAM(0),
    TFR(1)
}

private class NotamTypeSerializer : KSerializer<NotamType> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("NotamType", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: NotamType) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): NotamType = intToNotamType(decoder.decodeInt())

    private fun intToNotamType(int: Int) = when (int) {
        NotamType.NOTAM.value -> NotamType.NOTAM
        NotamType.TFR.value -> NotamType.TFR
        else -> NotamType.INVALID
    }

}
