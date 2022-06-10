package com.avonfitzgerald.infinitelive.core

import arrow.core.Either
import arrow.core.flatMap
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

// Infinite Flight API Error Codes :
// https://infiniteflight.com/guide/developer-reference/live-api/sessions#liveapiresponse
private const val OK = 0
private const val USER_NOT_FOUND = 1
private const val MISSING_REQUEST_PARAMETERS = 2
private const val ENDPOINT_ERROR = 3
private const val NOT_AUTHORIZED = 4
private const val SERVER_NOT_FOUND = 5
private const val FLIGHT_NOT_FOUND = 6
private const val NO_ATIS_AVAILABLE = 7
private const val AIRPORT_NOT_FOUND = 8

internal data class ApiResponse(val errorCode: Int, val result: String)

internal fun deserialize(data: String) = data.toApiResponse().flatMap { it.checkError() }

private fun ApiResponse.checkError() = when (errorCode) {
    OK -> Either.Right(result)
    USER_NOT_FOUND -> Either.Left(UserNotFound(result))
    MISSING_REQUEST_PARAMETERS -> Either.Left(MissingRequestParameters(result))
    ENDPOINT_ERROR -> Either.Left(EndpointError(result))
    NOT_AUTHORIZED -> Either.Left(NotAuthorized(result))
    SERVER_NOT_FOUND -> Either.Left(ServerNotFound(result))
    FLIGHT_NOT_FOUND -> Either.Left(FlightNotFound(result))
    NO_ATIS_AVAILABLE -> Either.Left(NoAtisAvailable(result))
    AIRPORT_NOT_FOUND -> Either.Left(AirportNotFound(result))
    else -> Either.Left(UndefinedErrorCode(errorCode, result))
}

private fun String.toApiResponse() = Either.catch {

    val (errorCode, result) = Json.parseToJsonElement(this).let {
        val errorCode = it.jsonObject["errorCode"]!!.jsonPrimitive.int
        val result = it.jsonObject["result"]!!.toString()
        errorCode to result
    }

    ApiResponse(errorCode, result)
}.mapLeft { MalformedResponse(0, this) }
