package com.avonfitzgerald.infinitelive.core

/**
 * Internal Error sent by the Infinite Flight Live API.
 *
 * The exceptions that inherit from [InfiniteLiveException] are derived from an enum value "errorCode" in
 * the [LiveAPIResponse](https://infiniteflight.com/guide/developer-reference/live-api/sessions#liveapiresponse) object.
 */
sealed class InfiniteLiveException(message: String) : Exception(message) {

    /**
     * API has sent a response not in the standard form, typically a 404.
     *
     * Standard form is defined as :
     * ```json
     * {
     *   "errorCode": <Int>,
     *   "result": <T>
     * }
     * ```
     * With Int and T in their JSON representation.
     *
     * @property httpStatusCode [HTTP Status Code](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html)
     */
    class MalformedResponse(val httpStatusCode: Int, val body: String) :
        InfiniteLiveException("httpStatusCode=$httpStatusCode, body=$body")

    /**
     * API has sent an undocumented internal
     * [error code](https://infiniteflight.com/guide/developer-reference/live-api/sessions#liveapiresponse)
     * different from 0 (which is defined as OK).
     *
     * @property errorCode Unknown Error Code
     * @property result Serialized JSON
     */
    class UndefinedErrorCode(val errorCode: Int, val result: String) :
        InfiniteLiveException("errorCode=$errorCode, result=$result")

    class UserNotFound(message: String) : InfiniteLiveException(message)
    class MissingRequestParameters(message: String) : InfiniteLiveException(message)
    class EndpointError(message: String) : InfiniteLiveException(message)
    class NotAuthorized(message: String) : InfiniteLiveException(message)
    class ServerNotFound(message: String) : InfiniteLiveException(message)
    class FlightNotFound(message: String) : InfiniteLiveException(message)
    class NoAtisAvailable(message: String) : InfiniteLiveException(message)
    class AirportNotFound(message: String) : InfiniteLiveException(message)

}

typealias MalformedResponse = InfiniteLiveException.MalformedResponse
typealias UndefinedErrorCode = InfiniteLiveException.UndefinedErrorCode

typealias UserNotFound = InfiniteLiveException.UserNotFound
typealias MissingRequestParameters = InfiniteLiveException.MissingRequestParameters
typealias EndpointError = InfiniteLiveException.EndpointError
typealias NotAuthorized = InfiniteLiveException.NotAuthorized
typealias ServerNotFound = InfiniteLiveException.ServerNotFound
typealias FlightNotFound = InfiniteLiveException.FlightNotFound
typealias NoAtisAvailable = InfiniteLiveException.NoAtisAvailable
typealias AirportNotFound = InfiniteLiveException.AirportNotFound
