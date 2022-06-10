package com.avonfitzgerald.infinitelive.endpoint.session.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = SessionTypeSerializer::class)
enum class SessionType(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    UNRESTRICTED(0),
    RESTRICTED(1)
}

private object SessionTypeSerializer : KSerializer<SessionType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("SessionType", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: SessionType) =
        encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): SessionType = intToSessionType(decoder.decodeInt())

    private fun intToSessionType(int: Int) = when (int) {
        SessionType.UNRESTRICTED.value -> SessionType.UNRESTRICTED
        SessionType.RESTRICTED.value -> SessionType.RESTRICTED
        else -> SessionType.INVALID
    }
}