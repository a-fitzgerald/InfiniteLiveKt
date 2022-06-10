package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade#graderuledefinition)
 *
 * @property name Name of the rule.
 * @property description Description of the rule.
 * @property property The property of the GradeInfo object the rule relates to.
 * @property operator Math comparison.
 * @property period Time period in which the rule must be met.
 * @property order Order of the Rule within the rules property of the Grade object.
 */
@Serializable
data class GradeRuleDefinition(
    val name: String,
    val description: String,
    val property: String,
    val operator: GradeOperator,
    val period: Double,
    val order: Int
)