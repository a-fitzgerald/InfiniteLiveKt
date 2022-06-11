package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * [Official Documentation](https://infiniteflight.com/guide/developer-reference/live-api/user-grade#graderule)
 *
 * @property ruleIndex Index of the rule in the rules property of the [Grade] object.
 * @property referenceValue The requirement value.
 * @property userValue 	The value of the user for this property.
 * @property state Fail/OK/Warning
 * @property userValueString The value of the user, nicely formatted.
 * @property referenceValueString The requirement value, nicely formatted.
 * @property definition Definition of the rule.
 */
@Serializable
data class GradeRule(
    val ruleIndex: Int,
    val referenceValue: Double,
    val userValue: Double,
    val state: GradeState = GradeState.INVALID,
    val userValueString: String,
    val referenceValueString: String,
    val definition: GradeRuleDefinition
)