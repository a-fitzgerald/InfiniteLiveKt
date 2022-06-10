package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade#gradeconfiguration)
 *
 * @property grades Array containing all grades.
 * @property gradeIndex The index of the grades property that the user holds.
 * @property ruleDefinitions Definition for the rules required for each grade.
 */
@Serializable
data class GradeConfiguration(
    val grades: List<Grade>,
    val gradeIndex: Int,
    val ruleDefinitions: List<GradeRuleDefinition>
)
