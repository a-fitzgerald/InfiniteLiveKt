package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = GradeOperatorSerializer::class)
enum class GradeOperator(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    GREATER_THAN(0),
    LESSER_THAN(1),
    GREATER_THAN_OR_EQUAL(2),
    LESSER_THAN_OR_EQUAL(3),
    EQUAL(4),
    DIFFERENT_THAN(5)
}

private object GradeOperatorSerializer : KSerializer<GradeOperator> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("GradeOperator", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: GradeOperator) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): GradeOperator = intToGradeOperator(decoder.decodeInt())

    private fun intToGradeOperator(int: Int) = when (int) {
        GradeOperator.GREATER_THAN.value -> GradeOperator.GREATER_THAN
        GradeOperator.LESSER_THAN.value -> GradeOperator.LESSER_THAN
        GradeOperator.GREATER_THAN_OR_EQUAL.value -> GradeOperator.GREATER_THAN_OR_EQUAL
        GradeOperator.LESSER_THAN_OR_EQUAL.value -> GradeOperator.LESSER_THAN_OR_EQUAL
        GradeOperator.EQUAL.value -> GradeOperator.EQUAL
        GradeOperator.DIFFERENT_THAN.value -> GradeOperator.DIFFERENT_THAN
        else -> GradeOperator.INVALID
    }

}