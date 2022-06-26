# InfiniteLiveKt

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Maven Central](https://img.shields.io/maven-central/v/com.avonfitzgerald/infinitelive.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.avonfitzgerald%22%20AND%20a:%22infinitelive%22)

Fast-track development with the 
[Infinite Flight Live API](https://infiniteflight.com/guide/developer-reference/live-api/overview). 

InfiniteLiveKt's main objective is to provide a safe way of handling the API through Kotlin's strong type system. 
Thus, no [exceptions](https://kotlinlang.org/docs/exceptions.html) are thrown and any signatures properly
describe the behaviour of any function. This is accomplished using the [Λrrow library](https://arrow-kt.io/) and its 
[Either](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/) class.

Additionally, the InfiniteLiveKt library provides implementations of all publicly defined models and endpoints of 
the [Infinite Flight Live API](https://infiniteflight.com/guide/developer-reference/live-api/overview).

## Setup

### Gradle

Add dependencies in your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("com.avonfitzgerald:infinitelive:2.0.3")
}
```

Make sure Maven Central is in your list of repositories:
```kotlin
repositories {
    mavenCentral()
}
```

### Maven

Add dependencies in your `pom.xml` file:
```xml
<dependency>
  <groupId>com.avonfitzgerald</groupId>
  <artifactId>infinitelive</artifactId>
  <version>2.0.3</version>
</dependency>
```

## Getting Started

### Basic Configuration

Before anything else, you will need to have a valid API Key to access the Infinite Flight Live API.
Details on how to obtain your key are available in the 
[official documentation](https://infiniteflight.com/guide/developer-reference/live-api/overview#obtaining-an-api-key).

First things first, you will need to instantiate `InfiniteLive` with your API key.

```kotlin
fun main() {
    val live = InfiniteLive(INFINITE_FLIGHT_API_KEY)
}
```

By default, InfiniteLive will use an [HttpClient from Ktor](https://ktor.io/docs/create-client.html) with the
[CIO engine](https://ktor.io/docs/http-client-engines.html#cio).
You can change the engine by passing your preferred client as the second parameter.

```kotlin
fun main() {
    val live = InfiniteLive(INFINITE_FLIGHT_API_KEY, HttpClient(OkHttp))
}
```
 
If you are configuring your own engine do not forget to include
the [relevant dependencies](https://ktor.io/docs/client-dependencies.html).

### Handling Requests

`InfiniteLive` has two available methods to make HTTPS requests to the API :

```kotlin
suspend fun <T> getRequest(endpoint: Get<T>): Either<Throwable, T>
suspend fun <T> postRequest(endpoint: Post<T>): Either<Throwable, T>
```

`T` is the generic type of your object of interest after deserialization of the JSON response from the API.
This object can be a session/server, a flight, a list of airports or anything else.

`Get` and `Post` are both aliases of `InfiniteLiveEndpoint.Get` and `InfiniteLiveEndpoint.Post`respectively.
They both inherit from the **sealed** class InfiniteLiveEndpoint.

Naturally, as the library performs network calls, it enforces the use of
[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) as both methods are marked as `suspend`.

When you use either of these methods, it will execute the following instructions:

1. Fetch JSON data from the API. If it fails, send back an
[Either.Left](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/-left/index.html) containing the error.

2. Check if the JSON is in the correct format with an
[internal error code](https://infiniteflight.com/guide/developer-reference/live-api/sessions#liveapiresponse)
and an appropriate result. If it fails, send back an
   [Either.Left](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/-left/index.html) containing
a `MalformedResponse` exception (more info about error handling below) with the
[HTTP Status Code](https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html) and the raw response from the server.

3. Check if the Infinite Flight API responded with an OK
   [internal error code](https://infiniteflight.com/guide/developer-reference/live-api/sessions#liveapiresponse).
If it fails, send back an [Either.Left](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/-left/index.html)
containing an `InfiniteLiveException` with the serialized message.

4. Perform the deserialization to obtain an
[Either.Right](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/-right/index.html)
containing the deserialized object `T` defined in the `InfiniteLiveEndpoint`. If it fails, send back an
   [Either.Left](https://arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/-left/index.html) containing the error.

### Error Handling

When you perform a request there are a multitude of ways your code might fail.
Thankfully, with the help of functional programming we can appropriately handle them whenever an error occurs, 
thus avoiding unexpected failures which may cause a fatal crash.

There are two major type of exceptions you need to be aware of :
- InfiniteLiveException
- [Exception](https://kotlinlang.org/docs/exceptions.html)

With `Either` handling errors becomes a trivial matter, and you can write proper fault-tolerant code.

Example:

````kotlin
suspend fun getAtis(sessionId: String, airportIcao: String): String = live.getRequest(
   GetAirportAtis(sessionId, airportIcao)
).mapLeft {
   when (it) {
      is NoAtisAvailable -> "ATIS is not available"
      is InfiniteLiveException -> "Infinite Flight Failure"
      else -> "Internal Server Error"
   }
}.merge()
````

#### InfiniteLiveException

This exception occurs if the Infinite Flight Live API has responded with an invalid or valid JSON response.
All exceptions inheriting from `InfiniteLiveException` have defined aliases
(e.g. `typealias MalformedResponse = InfiniteLiveException.MalformedResponse`)

**Invalid JSON** (no errorCode or result field in the raw JSON response):
- MalformedException (includes the properties httpStatusCode and body)

**Valid JSON**:
- UserNotFound
- MissingRequestParameters
- EndpointError
- NotAuthorized
- ServerNotFound
- FlightNotFound
- NoAtisAvailable
- AirportNotFound
- UndefinedErrorCode (includes the properties errorCode and result serialized)

#### Exception (Not InfiniteLiveException)

This exception occurs if something went wrong on your side such as a network error
(e.g. not connected to the internet), JSON deserialization error or else.

### Predefined Get/Post Requests

The InfiniteLiveKt library provides implementations of all publicly defined models and
endpoints of the Infinite Flight Live API :
- [GetSessions](https://infiniteflight.com/guide/developer-reference/live-api/sessions)
- [GetFlights](https://infiniteflight.com/guide/developer-reference/live-api/flights)
- [GetFlightRoute](https://infiniteflight.com/guide/developer-reference/live-api/flight-route)
- [GetFlightPlan](https://infiniteflight.com/guide/developer-reference/live-api/flight-plan)
- [GetActiveAtcFrequencies](https://infiniteflight.com/guide/developer-reference/live-api/atc)
- [PostUserStats](https://infiniteflight.com/guide/developer-reference/live-api/user-stats)
- [GetUserGrade](https://infiniteflight.com/guide/developer-reference/live-api/user-grade)
- [GetAirportAtis](https://infiniteflight.com/guide/developer-reference/live-api/atis)
- [GetAirportStatus](https://infiniteflight.com/guide/developer-reference/live-api/airport-status)
- [GetWorldStatus](https://infiniteflight.com/guide/developer-reference/live-api/world-status)
- [GetOceanicTracks](https://infiniteflight.com/guide/developer-reference/live-api/oceanic-tracks)
- [GetUserFlights](https://infiniteflight.com/guide/developer-reference/live-api/user-flights)
- [GetUserFlight](https://infiniteflight.com/guide/developer-reference/live-api/user-flight)
- [GetUserAtcSessions](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-sessions)
- [GetUserAtcSession](https://infiniteflight.com/guide/developer-reference/live-api/user-atc-session)
- [GetNotams](https://infiniteflight.com/guide/developer-reference/live-api/notams)

Example:

```kotlin
suspend fun printFlightsFromServer(serverId: String): Either<Throwable, Unit> = either {
   val flights = live.getRequest(GetFlights(serverId)).bind()
   flights.forEach(::println)
}
```

Predefined endpoints from InfiniteLiveKt do not deserialize deprecated fields from the
[Infinite Flight Live API](https://infiniteflight.com/guide/developer-reference/live-api/overview)
and do not fall back to default values except for enums (they will default to their associated INVALID field).

### Custom Get/Post Requests

If somehow an endpoint is missing, or you want to provide your own deserialization strategy
you can define a custom `InfiniteLiveEndpoint`. To do so, simply inherit from `Get` (alias of `InfiniteLiveEndpoint.Get`)
or `Post` (alias of `InfiniteLiveEndpoint.Post`) depending on the HTTPS request.
The Infinite Flight Live API does not use other HTTP methods such as PUT or DELETE.

Example (Get):
```kotlin
class GetSessions : Get<List<SessionInfo>>("sessions") {
    override fun deserialize(data: String): List<SessionInfo> = Json.decodeFromString(data)
}
```

Example (Post):
```kotlin
// Fictitious endpoint for the purpose of the example
class PostRequest(serializedJson: String) : Post<SomeObject>("some-post", serializedJson) {
    override fun deserialize(data: String): List<SomeObject> = Json.decodeFromString(data)
}
```

## License

[MIT License](LICENSE.txt) : Copyright (c) 2022 [Avon FitzGerald](https://github.com/A-FitzGerald)

