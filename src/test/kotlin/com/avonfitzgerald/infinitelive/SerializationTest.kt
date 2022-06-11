package com.avonfitzgerald.infinitelive

import arrow.core.Either
import arrow.typeclasses.Monoid
import com.avonfitzgerald.infinitelive.core.*
import com.avonfitzgerald.infinitelive.endpoint.airport.GetActiveAtcFrequencies
import com.avonfitzgerald.infinitelive.endpoint.airport.GetAirportAtis
import com.avonfitzgerald.infinitelive.endpoint.airport.GetAirportStatus
import com.avonfitzgerald.infinitelive.endpoint.airport.GetWorldStatus
import com.avonfitzgerald.infinitelive.endpoint.flight.GetFlightPlan
import com.avonfitzgerald.infinitelive.endpoint.flight.GetFlightRoute
import com.avonfitzgerald.infinitelive.endpoint.flight.GetFlights
import com.avonfitzgerald.infinitelive.endpoint.misc.GetNotams
import com.avonfitzgerald.infinitelive.endpoint.misc.GetOceanicTracks
import com.avonfitzgerald.infinitelive.endpoint.session.GetSessions
import com.avonfitzgerald.infinitelive.endpoint.session.model.SessionInfo
import com.avonfitzgerald.infinitelive.endpoint.user.*
import com.avonfitzgerald.infinitelive.endpoint.user.model.PaginatedList
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserAtcSession
import com.avonfitzgerald.infinitelive.endpoint.user.model.UserFlight
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.reflect.KClass
import kotlin.test.Test

private const val LIMIT_FLIGHT_ID_PER_SESSION = 3
private const val LIMIT_USER_IDS = 5

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SerializationTest {

    private val live = InfiniteLive(System.getenv("INFINITE_FLIGHT_API_KEY")!!)

    companion object {

        private var sessionIds = emptyList<String>()
        private var serverFlightIds = mutableListOf<Pair<String, String>>()
        private var userIds = mutableListOf<String>()

        private var historicalFlights = mutableListOf<Pair<String, PaginatedList<List<UserFlight>>>>()
        private var historicalAtcSessions = mutableListOf<Pair<String, PaginatedList<List<UserAtcSession>>>>()

        @JvmStatic
        private fun sessionIds() = sessionIds

        @JvmStatic
        private fun serverFlightIds() = serverFlightIds
            .groupBy { it.first } // group by sessionId
            .mapValues { (_, ids) -> ids.map { it.second } } // filter out sessionId from Pair
            .mapValues { (_, flightIds) ->
                flightIds.shuffled().take(LIMIT_FLIGHT_ID_PER_SESSION)
            } // reduce number of flightId / server
            .mapValues { (serverId, flightIds) -> flightIds.map { serverId to it } } // Converting to list of arguments
            .map { (_, ids) -> ids }
            .flatten()
            .map { Arguments.of(it.first, it.second) }

        @JvmStatic
        private fun userIds() = Stream.of(Arguments.of(userIds.shuffled().take(LIMIT_USER_IDS)))

        @JvmStatic
        private fun userIdsUnique() = userIds.shuffled().take(LIMIT_USER_IDS)

        @JvmStatic
        private fun liveAirportsUnique() = sessionIds
            .map { it to listOf("EGLL", "LFPG", "RPLL") }
            .map { (serverId, airportIcaos) -> airportIcaos.map { serverId to it } }
            .flatten()
            .map { (serverId, airportIcao) -> Arguments.of(serverId, airportIcao) }

        @JvmStatic
        private fun historicalFlightUnique() = historicalFlights
            .take(LIMIT_USER_IDS)
            .mapNotNull { (userId, flights) -> flights.data?.randomOrNull()?.id?.let { userId to it } }
            .map { (userId, flightId) -> Arguments.of(userId, flightId) }

        @JvmStatic
        private fun historicalAtcSessionUnique() = historicalAtcSessions
            .take(LIMIT_USER_IDS)
            .mapNotNull { (userId, atcSessions) -> atcSessions.data?.randomOrNull()?.id?.let { userId to it } }
            .map { (userId, atcSession) -> Arguments.of(userId, atcSession) }

    }

    private inline fun <reified T> Either<Throwable, *>.assertEitherInfiniteLive(tolerance: KClass<in T>? = null)
            where T : InfiniteLiveException {

        val isPass = bifoldMap(
            Monoid.boolean(),
            { t -> tolerance?.let { t is T } ?: false },
            { true }
        )

        if (!isPass) tapLeft(Throwable::printStackTrace)
        assert(isPass)
    }

    @Test
    @Order(1)
    fun `Endpoint - GetSessions`() {
        val eSessions = runBlocking { live.getRequest(GetSessions()) }
        eSessions.tap { sessionIds = it.map(SessionInfo::id) }
        eSessions.assertEitherInfiniteLive<InfiniteLiveException>()
    }

    @ParameterizedTest
    @Order(2)
    @MethodSource("sessionIds")
    fun `Endpoint - GetFlights`(sessionId: String) {

        val eFlights = runBlocking { live.getRequest(GetFlights(sessionId)) }

        eFlights.tap { flights ->

            flights.forEach {
                serverFlightIds += sessionId to it.flightId
            }

            flights.forEach {
                userIds += it.userId
            }

        }

        eFlights.assertEitherInfiniteLive<InfiniteLiveException>()
    }

    @ParameterizedTest
    @Order(3)
    @MethodSource("userIdsUnique")
    fun `Endpoint - GetUserFlights`(userId: String) = runBlocking {
        live.getRequest(GetUserFlights(userId)).tap { historicalFlights += userId to it }
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @Order(4)
    @MethodSource("userIdsUnique")
    fun `Endpoint - GetUserAtcSessions`(userId: String) = runBlocking {
        live.getRequest(GetUserAtcSessions(userId)).tap { historicalAtcSessions += userId to it }
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @MethodSource("serverFlightIds")
    fun `Endpoint - GetFlightRoute`(sessionId: String, flightId: String) = runBlocking {
        live.getRequest(GetFlightRoute(sessionId, flightId))
    }.assertEitherInfiniteLive(FlightNotFound::class)

    @ParameterizedTest
    @MethodSource("serverFlightIds")
    fun `Endpoint - GetFlightPlan`(sessionId: String, flightId: String) = runBlocking {
        live.getRequest(GetFlightPlan(sessionId, flightId))
    }.assertEitherInfiniteLive(FlightNotFound::class)

    @ParameterizedTest
    @MethodSource("sessionIds")
    fun `Endpoint - GetActiveAtcFrequencies`(sessionId: String) = runBlocking {
        live.getRequest(GetActiveAtcFrequencies(sessionId))
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @MethodSource("userIds")
    fun `Endpoint - PostUserStats`(userIds: List<String>) {
        if (userIds.size > 25) throw IllegalArgumentException("userIds too large (limit is 25)")
        val eUsers = runBlocking { live.postRequest(PostUserStats(userIds = userIds)) }
        eUsers.assertEitherInfiniteLive(UserNotFound::class)
    }

    @ParameterizedTest
    @MethodSource("userIdsUnique")
    fun `Endpoint - GetUserGrade`(userId: String) = runBlocking {
        live.getRequest(GetUserGrade(userId))
    }.assertEitherInfiniteLive(UserNotFound::class)

    @ParameterizedTest
    @MethodSource("liveAirportsUnique")
    fun `Endpoint - GetAirportAtis`(sessionId: String, airportIcao: String) = runBlocking {
        live.getRequest(GetAirportAtis(sessionId, airportIcao))
    }.assertEitherInfiniteLive(NoAtisAvailable::class)

    @ParameterizedTest
    @MethodSource("liveAirportsUnique")
    fun `Endpoint - GetAirportStatus`(sessionId: String, airportIcao: String) = runBlocking {
        live.getRequest(GetAirportStatus(sessionId, airportIcao))
    }.assertEitherInfiniteLive(AirportNotFound::class)

    @ParameterizedTest
    @MethodSource("sessionIds")
    fun `Endpoint - GetWorldStatus`(sessionId: String) = runBlocking {
        live.getRequest(GetWorldStatus(sessionId))
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @Test
    fun `Endpoint - GetOceanicTracks`() = runBlocking {
        live.getRequest(GetOceanicTracks())
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @MethodSource("historicalFlightUnique")
    fun `Endpoint - GetUserFlight`(userId: String, flightId: String) = runBlocking {
        live.getRequest(GetUserFlight(userId, flightId))
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @MethodSource("historicalAtcSessionUnique")
    fun `Endpoint - GetUserAtcSession`(userId: String, atcSessionId: String) = runBlocking {
        live.getRequest(GetUserAtcSession(userId, atcSessionId))
    }.assertEitherInfiniteLive<InfiniteLiveException>()

    @ParameterizedTest
    @MethodSource("sessionIds")
    fun `Endpoint - GetNotams`(sessionId: String) = runBlocking {
        live.getRequest(GetNotams(sessionId))
    }.assertEitherInfiniteLive<InfiniteLiveException>()

}