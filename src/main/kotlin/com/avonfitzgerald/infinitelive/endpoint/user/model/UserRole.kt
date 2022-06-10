package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = UserRoleSerializer::class)
enum class UserRole(val value: Int) {

    /**
     * An undocumented enum constant
     */
    INVALID(-1),

    INFINITE_FLIGHT_STAFF(1),
    MODERATORS(2),
    IFATC_MEMBERS(64)
}

private object UserRoleSerializer : KSerializer<UserRole> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("UserRole", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: UserRole) = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): UserRole = intToUserRole(decoder.decodeInt())

    private fun intToUserRole(int: Int) = when (int) {
        UserRole.INFINITE_FLIGHT_STAFF.value -> UserRole.INFINITE_FLIGHT_STAFF
        UserRole.MODERATORS.value -> UserRole.MODERATORS
        UserRole.IFATC_MEMBERS.value -> UserRole.IFATC_MEMBERS
        else -> UserRole.INVALID
    }
}