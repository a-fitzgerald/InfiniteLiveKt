package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade#gradeinfo)
 *
 * @property userId Unique identifier for the user.
 *
 * @property virtualOrganization The virtual organization of the user's forum account if linked. Can be null if not set.
 *
 * @property discourseUsername The user's forum username if the account is linked.
 * If the account isn't linked, this will be null.
 *
 * @property roles A list of roles a user has been assigned. See below for a list of main roles.
 *
 * @property gradeDetails Full Grade Table.
 *
 * @property violationCountByLevel Violation count split up by levels (Level 1/2/3).
 *
 * @property totalXP Total XP obtained in multiplayer.
 *
 * @property atcOperations Total number of ATC Operations.
 *
 * @property atcRank ATC Rank on the Expert Server. Can be null if user isn't an IFATC controller.
 *
 * @property total12MonthsViolations Total amount of Level 1, 2, and 3 violations received in the last 12 months.
 *
 * @property lastLevel1ViolationDate Date of the user's last Level 1 violation.
 *
 * @property lastReportViolationDate Date of the user's last Level 2 or 3 violation (report).
 * Defaults to 0001-01-01T00:00:00 if the user does not have any reports.
 */
@Serializable
data class GradeInfo(
    val userId: String,
    val virtualOrganization: String?,
    val discourseUsername: String?,
    val roles: List<UserRole>,
    val gradeDetails: GradeConfiguration,
    val violationCountByLevel: ViolationCountByLevel,
    val totalXP: Double,
    val atcOperations: Int,
    val atcRank: Int?,
    val total12MonthsViolations: Int,
    val lastLevel1ViolationDate: String,
    val lastReportViolationDate: String
)
