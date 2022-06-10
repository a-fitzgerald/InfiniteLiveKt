package com.avonfitzgerald.infinitelive.core

import arrow.core.Either
import arrow.core.flatMap
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val BASE_URL_API_INFINITE_FLIGHT = "https://api.infiniteflight.com/public/v2/"

/**
 * Fast-track development with the
 * [Infinite Flight Live API](https://infiniteflight.com/guide/developer-reference/live-api/overview).
 *
 * InfiniteLiveKt's main objective is to provide a safe way of handling the API through Kotlin's strong type system.
 * Thus, no [exceptions](https://kotlinlang.org/docs/exceptions.html) are thrown and any signatures properly
 * describe the behaviour of any function.
 * This is accomplished using the [Λrrow library](https://arrow-kt.io/) and its [Either] class.
 *
 * Additionally, the InfiniteLiveKt library provides implementations of all publicly defined models and endpoints of
 * the Infinite Flight Live API inside the package com.avonfitzgerald.infinitelive.endpoint.
 *
 * @param apiKey API Key to the Infinite Flight Live API.
 * Details on how to obtain this key are available in the Infinite Flight Live API
 * [official documentation](https://infiniteflight.com/guide/developer-reference/live-api/overview#obtaining-an-api-key).
 * @param client Instance of the HttpClient with the provided [engine](https://ktor.io/docs/http-client-engines.html).
 * All network calls are in HTTPS. The default engine is [CIO].
 */
class InfiniteLive(private val apiKey: String, private val client: HttpClient = HttpClient(CIO)) {

    /**
     * Performs an HTTP GET request and parses the result.
     *
     * Example:
     * ```kotlin
     * // Returns an Either<Throwable, Boolean>
     * suspend fun hasActiveUsers() = infiniteLive.getRequest(GetSession()).map { servers ->
     *   servers.any {
     *     it.userCount > 0
     *   }
     * }
     * ```
     *
     * @param endpoint Instance of [InfiniteLiveEndpoint.Get] (or alias [Get]).
     * @return Throwable ([InfiniteLiveException], or else) or T which is the deserialized object of interest
     * defined in the [InfiniteLiveEndpoint] instance.
     */
    suspend fun <T> getRequest(endpoint: Get<T>) = rawGet(endpoint.path).parse(endpoint)

    /**
     * Performs an HTTP POST request and parses the result.
     *
     * Example:
     * ```kotlin
     * // Returns an Int. Default is zero.
     * suspend fun findHighestAtcOps(usernames: List<String>) = mostAtcOps(
     *   UserStatsBody(discourseNames = usernames)
     * ).getOrElse { 0 }
     *
     * // Returns an Either<Throwable, Int>
     * suspend fun maxAtcOps(body: UserStatsBody) = infiniteLive
     *   .postRequest(PostUserStats(body))
     *   .map { users ->
     *     users.maxOf { it.atcOperations }
     *   }
     * ```
     *
     * @param endpoint Instance of [InfiniteLiveEndpoint.Post] (or alias [Post]).
     * @return Throwable ([InfiniteLiveException], or else) or T which is the deserialized object of interest
     * defined in the [InfiniteLiveEndpoint].
     */
    suspend fun <T> postRequest(endpoint: Post<T>) = rawPost(endpoint.path, endpoint.serializedJson).parse(endpoint)

    private suspend fun <T> Either<Throwable, HttpResponse>.parse(endpoint: InfiniteLiveEndpoint<T>) = flatMap { res ->
        deserialize(res.bodyAsText()).mapLeft { it.addDetailIfMalformedResponse(res.status) }
    }.flatMap {
        endpoint.parse(it)
    }

    private fun Throwable.addDetailIfMalformedResponse(code: HttpStatusCode) = when (this) {
        is MalformedResponse -> MalformedResponse(code.value, body)
        else -> this
    }

    private suspend fun rawGet(path: String): Either<Throwable, HttpResponse> = Either.catch {
        client.get("${BASE_URL_API_INFINITE_FLIGHT}$path") {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
        }
    }

    private suspend fun rawPost(path: String, body: String): Either<Throwable, HttpResponse> = Either.catch {
        client.post("${BASE_URL_API_INFINITE_FLIGHT}$path") {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }

}
