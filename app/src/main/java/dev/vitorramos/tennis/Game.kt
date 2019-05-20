package dev.vitorramos.tennis

class Game(
    hostName: String,
    guestName: String,
    private val games: Int,
    private val sets: Int,
    private val onScoreChanged: (HashMap<String, String>, HashMap<String, String>) -> Unit,
    private val onMatchFinished: () -> Unit
) {
    private val hostScore = Score(hostName)
    private val guestScore = Score(guestName)

    fun addPoint(whichPlayer: WhichPlayer) {
        var consumed = true
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        val otherPlayer = if (whichPlayer == WhichPlayer.HOST) guestScore else hostScore

        when (scoringPlayer.points) {
            // 0, 15, 30
            0, 1, 2 -> scoringPlayer.points++
            // 40
            3 -> {
                when (otherPlayer.points) {
                    0, 1, 2 -> {
                        addGame(whichPlayer)
                        consumed = false
                    }
                    3 -> scoringPlayer.points++
                    4 -> otherPlayer.points--
                }
            }
            // A
            4 -> {
                addGame(whichPlayer)
                consumed = false
            }
        }
        if (consumed) onScoreChanged(getHashMap(hostScore), getHashMap(guestScore))
    }

    private fun addGame(whichPlayer: WhichPlayer) {
        hostScore.points = 0
        guestScore.points = 0
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        scoringPlayer.games++
        if (scoringPlayer.games == games) addSet(whichPlayer)
        else onScoreChanged(getHashMap(hostScore), getHashMap(guestScore))
    }

    private fun addSet(whichPlayer: WhichPlayer) {
        hostScore.games = 0
        guestScore.games = 0
        val scoringPlayer = if (whichPlayer == WhichPlayer.HOST) hostScore else guestScore
        scoringPlayer.sets++
        if (scoringPlayer.sets == sets) onMatchFinished()
        else onScoreChanged(getHashMap(scoringPlayer), getHashMap(guestScore))
    }

    private fun getHashMap(score: Score): HashMap<String, String> {
        val hashMap = hashMapOf<String, String>()
        hashMap[MAP_FIELD_POINTS] = getFormattedPoints(score.points)
        hashMap[MAP_FIELD_GAMES] = score.games.toString()
        hashMap[MAP_FIELD_SETS] = score.sets.toString()
        return hashMap
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

    enum class WhichPlayer {
        HOST,
        GUEST
    }

    companion object {
        const val MAP_FIELD_POINTS = "points"
        const val MAP_FIELD_GAMES = "games"
        const val MAP_FIELD_SETS = "sets"
    }
}
