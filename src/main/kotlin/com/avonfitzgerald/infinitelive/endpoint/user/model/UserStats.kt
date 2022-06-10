package com.avonfitzgerald.infinitelive.endpoint.user.model

import com.avonfitzgerald.infinitelive.endpoint.common.AtcRank
import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-stats#userstats)
 *
 * @property userId Unique identifier for the user.
 *
 * @property virtualOrganization The virtual organization of the user's forum account if linked.
 * Can be null if not set.
 *
 * @property discourseUsername The user's forum username if the account is linked.
 * If the account isn't linked, this will be null.
 *
 * @property roles A list of roles a user has been assigned. See below for a list of main roles.
 *
 * @property errorCode Status code of user query. Not in use for this endpoint.
 *
 * @property onlineFlights Number of flights carried out in multiplayer.
 *
 * @property violations Number of Level 1, 2 and 3 violations the user received in multiplayer.
 *
 * @property violationCountByLevel Violation count split up by levels (Level 1/2/3).
 *
 * @property xp Total XP obtained in multiplayer.
 *
 * @property landingCount Total landings carried out in multiplayer.
 *
 * @property flightTime Total flight time in minutes in multiplayer.
 *
 * @property atcOperations Total number of ATC Operations.
 *
 * @property atcRank ATC Rank on the Expert Server. Can be null if user isn't an IFATC controller.
 *
 * @property grade The grade of the user, from 1 to 5.
 *
 * @property hash A short-form user identifier, shown in the app to identify anonymous users.
 */
@Serializable
data class UserStats(
    val userId: String,
    val virtualOrganization: String?,
    val discourseUsername: String?,
    val roles: List<UserRole>,
    val errorCode: Int,
    val onlineFlights: Int,
    val violations: Int,
    val violationCountByLevel: ViolationCountByLevel,
    val xp: Double,
    val landingCount: Int,
    val flightTime: Int,
    val atcOperations: Int,
    val atcRank: AtcRank?,
    val grade: Int,
    val hash: String
)
