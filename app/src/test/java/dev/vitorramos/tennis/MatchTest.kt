package dev.vitorramos.tennis

import dev.vitorramos.tennis.Match.Companion.addPoint
import dev.vitorramos.tennis.entity.MatchEntity
import org.junit.Test
import java.util.*

class MatchTest {
    // H 0  0  0  G 0  0  0
    // H 15 0  0  G 0  0  0
    @Test
    fun ca01() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3
        )

        val output = addPoint(Match.WhichPlayer.HOST, input)

        assert(output.hostPoints == 1)
        assert(getFormattedPoints(output.hostPoints) == "15")
    }

    // H 40 0  0  G 0  0  0
    // H 0  1  0  G 0  0  0
    @Test
    fun ca02() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3,
            hostPoints = 3
        )

        val output = addPoint(Match.WhichPlayer.HOST, input)

        assert(output.hostPoints == 0)
        assert(getFormattedPoints(output.hostPoints) == "0")

        assert(output.hostGames == 1)
    }

    // H 40 2  0  G 0  0  0
    // H 0  0  1  G 0  0  0
    @Test
    fun ca03() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3,
            hostPoints = 3,
            hostGames = 2
        )

        val output = addPoint(Match.WhichPlayer.HOST, input)

        assert(output.hostPoints == 0)
        assert(getFormattedPoints(output.hostPoints) == "0")

        assert(output.hostGames == 0)

        assert(output.hostSets == 1)
    }

    // H 40 0  0  G 40 0  0
    // H A  0  0  G 40 0  0
    @Test
    fun ca04() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3,
            hostPoints = 3,
            guestPoints = 3
        )

        val output = addPoint(Match.WhichPlayer.HOST, input)

        assert(output.hostPoints == 4)
        assert(getFormattedPoints(output.hostPoints) == "A")

        assert(output.guestPoints == 3)
        assert(getFormattedPoints(output.guestPoints) == "40")
    }

    // H A  0  0  G 40 0  0
    // H 0  1  0  G 0  0  0
    @Test
    fun ca05() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3,
            hostPoints = 4,
            guestPoints = 3
        )

        val output = addPoint(Match.WhichPlayer.HOST, input)

        assert(output.hostPoints == 0)
        assert(getFormattedPoints(output.hostPoints) == "0")

        assert(output.guestPoints == 0)
        assert(getFormattedPoints(output.guestPoints) == "0")

        assert(output.hostGames == 1)

        assert(output.guestGames == 0)
    }

    // H A  0  0  G 40 0  0
    // H 40 0  0  G 40 0  0
    @Test
    fun ca06() {
        val input = MatchEntity(
            started = Date().time,
            gamesToSet = 3,
            hostPoints = 4,
            guestPoints = 3
        )

        val output = addPoint(Match.WhichPlayer.GUEST, input)

        assert(output.hostPoints == 3)
        assert(getFormattedPoints(output.hostPoints) == "40")

        assert(output.guestPoints == 3)
        assert(getFormattedPoints(output.guestPoints) == "40")
    }
}
