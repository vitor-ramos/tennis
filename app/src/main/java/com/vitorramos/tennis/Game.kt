package com.vitorramos.tennis

class Game(
    hostName: String,
    guestName: String,
    private val games: Int,
    private val sets: Int,
    private val onMatchFinished: () -> Unit
) {
    private val hostScore = Score(hostName)
    private val guestScore = Score(guestName)

    fun addPoint(whichPlayer: PlayerType): HashMap<String, String> {
        var result: HashMap<String, String>? = null

        val scoringPlayer = if (whichPlayer == PlayerType.HOST) hostScore else guestScore
        val otherPlayer = if (whichPlayer == PlayerType.HOST) guestScore else hostScore

        when (scoringPlayer.points) {
            // 0, 15, 30
            0, 1, 2 -> scoringPlayer.points++
            // 40
            3 -> {
                when (otherPlayer.points) {
                    0, 1, 2 -> result = addGame(whichPlayer)
                    3 -> scoringPlayer.points++
                    4 -> otherPlayer.points--
                }
            }
            // A
            4 -> result = addGame(whichPlayer)
        }
        if (result == null) result = getHashMap(scoringPlayer)
        return result
    }

    private fun addGame(whichPlayer: PlayerType): HashMap<String, String> {
        hostScore.points = 0
        guestScore.points = 0
        val scoringPlayer = if (whichPlayer == PlayerType.HOST) hostScore else guestScore
        scoringPlayer.games++
        return if (scoringPlayer.games == games) addSet(whichPlayer) else getHashMap(scoringPlayer)
    }

    private fun addSet(whichPlayer: PlayerType): HashMap<String, String> {
        hostScore.games = 0
        guestScore.games = 0
        val scoringPlayer = if (whichPlayer == PlayerType.HOST) hostScore else guestScore
        scoringPlayer.sets++
        if (scoringPlayer.sets == sets) onMatchFinished()

        return getHashMap(scoringPlayer)
    }

    private fun getHashMap(score: Score): HashMap<String, String> {
        val hashMap = hashMapOf<String, String>()
        hashMap["points"] = getFormattedPoints(score.points)
        hashMap["games"] = score.games.toString()
        hashMap["sets"] = score.sets.toString()
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

    enum class PlayerType {
        HOST,
        GUEST
    }
}
