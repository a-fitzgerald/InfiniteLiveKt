package com.avonfitzgerald.infinitelive.core

import arrow.core.Either

/**
 * Endpoint of the [Infinite Flight Live API](https://infiniteflight.com/guide/developer-reference/live-api/sessions).
 * You will need to inherit from [Get] (alias of [InfiniteLiveEndpoint.Get]
 * or [Post] (alias of [InfiniteLiveEndpoint.Post]
 *
 * @property path relative path from https://api.infiniteflight.com/public/v2/
 */
sealed class InfiniteLiveEndpoint<T>(val path: String) {

    /**
     * @see InfiniteLiveEndpoint
     * @param path relative path from https://api.infiniteflight.com/public/v2/
     */
    abstract class Get<T>(path: String) : InfiniteLiveEndpoint<T>(path)

    /**
     * @see InfiniteLiveEndpoint
     * @param path relative path from [BASE_URL_API_INFINITE_FLIGHT]
     * @property serializedJson Serialized JSON https://api.infiniteflight.com/public/v2/
     */
    abstract class Post<T>(path: String, val serializedJson: String) : InfiniteLiveEndpoint<T>(path)

    /**
     * Deserialization of the JSON string of the field "result" in the
     * standard response from the Infinite Flight Live API.
     *
     * A standard response is in the format :
     * ```json
     * {
     *   "errorCode": <Int>,
     *   "result": <T>
     * }
     * ```
     * With Int and T in their JSON representation.
     *
     * @param data Serialized JSON
     */
    abstract fun deserialize(data: String): T

    internal fun parse(data: String) = Either.catch { deserialize(data) }

}

typealias Get<T> = InfiniteLiveEndpoint.Get<T>
typealias Post<T> = InfiniteLiveEndpoint.Post<T>
