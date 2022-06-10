package com.avonfitzgerald.infinitelive.endpoint.user.model

import kotlinx.serialization.Serializable

/**
 * @property level1 System issued violation.
 * @property level2 IFATC issued violation.
 * @property level3 IFATC issued violation.
 */
@Serializable
data class ViolationCountByLevel(val level1: Int, val level2: Int, val level3: Int)