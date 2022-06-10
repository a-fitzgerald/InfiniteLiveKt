package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GradeStateSerializer::class)
enum class GradeState(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    FAIL(0),
    OK(1),
    WARNING(2)
}

private class GradeStateSerializer : KSerializer<GradeState> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("GradeState", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: GradeState) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): GradeState = intToGradeState(decoder.decodeInt())

    private fun intToGradeState(int: Int) = when (int) {
        GradeState.FAIL.value -> GradeState.FAIL
        GradeState.OK.value -> GradeState.OK
        GradeState.WARNING.value -> GradeState.WARNING
        else -> GradeState.INVALID
    }

}