package com.avonfitzgerald.infinitelive.endpoint.common

import kotlinx.serialization.json.Json

val jsonDefault = Json {
    coerceInputValues = true
}