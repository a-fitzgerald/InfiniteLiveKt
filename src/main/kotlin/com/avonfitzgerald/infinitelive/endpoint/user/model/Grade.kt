package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade#grade)
 *
 * @property rules Rules to be met to obtain the grade.
 * @property index Index of the Grade in the grades property of the [GradeConfiguration] object.
 * @property name Name of the grade.
 * @property state Fail/OK/Warning.
 */
@Serializable
data class Grade(
    val rules: List<GradeRule>,
    val index: Int,
    val name: String,
    val state: GradeState = GradeState.INVALID
)