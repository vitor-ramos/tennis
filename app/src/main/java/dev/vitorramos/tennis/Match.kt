package dev.vitorramos.tennis

class Match(val id: Long, private val gamesToSet: Int, private val setsToMatch: Int) {
    val hostScore = Score()
    val guestScore = Score()

    fun addPoint(whichPlayer: WhichPlayer) {
        var consumed = true
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        val otherPlayer = if (whichPlayer == WhichPlayer.HOST) guestScore else hostScore

        when (scoringPlayer.points) {
            // 0, 15, 30
            0, 1, 2 -> scoringPlayer.points++
            // 40
            3 -> when (otherPlayer.points) {
                0, 1, 2 -> {
                    addGame(whichPlayer)
                    consumed = false
                }
                3 -> scoringPlayer.points++
                4 -> otherPlayer.points--
            }
            // A
            4 -> {
                addGame(whichPlayer)
                consumed = false
            }
        }
        if (consumed) {
            // TODO: score changed
        }
    }

    private fun addGame(whichPlayer: WhichPlayer) {
        hostScore.points = 0
        guestScore.points = 0
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        scoringPlayer.games++
        if (scoringPlayer.games == gamesToSet) addSet(whichPlayer)
        else {
            // TODO: score changed
        }
    }

    private fun addSet(whichPlayer: WhichPlayer) {
        hostScore.games = 0
        guestScore.games = 0
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        scoringPlayer.sets++
        if (scoringPlayer.sets == setsToMatch) {
            // TODO: match finished
        }
    }

    private fun getFormattedPoints(points: Int): String {
        return when (points) {
            0 -> "0"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            4 -> "A"
            else -> ""
        }
    }

    companion object {
        const val MAP_FIELD_POINTS = "points"
        const val MAP_FIELD_GAMES = "games"
        const val MAP_FIELD_SETS = "sets"
    }
}
