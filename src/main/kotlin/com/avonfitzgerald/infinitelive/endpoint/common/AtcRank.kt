package com.avonfitzgerald.infinitelive.endpoint.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = AtcRankSerializer::class)
enum class AtcRank(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    OBSERVER(0),
    TRAINEE(1),
    APPRENTICE(2),
    SPECIALIST(3),
    OFFICER(4),
    SUPERVISOR(5),
    RECRUITER(6),
    MANAGER(7)
}

internal object AtcRankSerializer : KSerializer<AtcRank> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("AtcRank", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: AtcRank) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): AtcRank = intToAtcRank(decoder.decodeInt())

    private fun intToAtcRank(int: Int) = when (int) {
        AtcRank.OBSERVER.value -> AtcRank.OBSERVER
        AtcRank.TRAINEE.value -> AtcRank.TRAINEE
        AtcRank.APPRENTICE.value -> AtcRank.APPRENTICE
        AtcRank.SPECIALIST.value -> AtcRank.SPECIALIST
        AtcRank.OFFICER.value -> AtcRank.OFFICER
        AtcRank.SUPERVISOR.value -> AtcRank.SUPERVISOR
        AtcRank.RECRUITER.value -> AtcRank.RECRUITER
        AtcRank.MANAGER.value -> AtcRank.MANAGER
        else -> AtcRank.INVALID
    }
}
