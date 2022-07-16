package com.avonfitzgerald.infinitelive.endpoint.common

import kotlinx.serialization.json.Json

internal val jsonDefault = Json {
    ignoreUnknownKeys = true // The Infinite Live API keeps changing (additions) in the same major version
    coerceInputValues = true // Fallback to default value if one is assigned (enums typically)
}