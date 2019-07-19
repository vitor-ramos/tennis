package dev.vitorramos.tennis

import dev.vitorramos.tennis.entity.MatchEntity
import java.lang.IllegalArgumentException
import java.util.*

class Match {
    companion object {
        fun addPoint(whichPlayer: WhichPlayer, matchEntity: MatchEntity): MatchEntity {
            val scoringPlayerPoints = if (whichPlayer == WhichPlayer.HOST) matchEntity.hostPoints
            else matchEntity.guestPoints

            val otherPlayerPoints = if (whichPlayer == WhichPlayer.HOST) matchEntity.guestPoints
            else matchEntity.hostPoints

            return when (scoringPlayerPoints) {
                // 0, 15, 30
                0, 1, 2 -> if (whichPlayer == WhichPlayer.HOST) matchEntity.copy(hostPoints = scoringPlayerPoints + 1)
                else matchEntity.copy(guestPoints = scoringPlayerPoints + 1)

                // 40
                3 -> when (otherPlayerPoints) {
                    0, 1, 2 -> addGame(whichPlayer, matchEntity)

                    3 -> if (whichPlayer == WhichPlayer.HOST) matchEntity.copy(hostPoints = scoringPlayerPoints + 1)
                    else matchEntity.copy(guestPoints = scoringPlayerPoints + 1)

                    4 -> if (whichPlayer == WhichPlayer.HOST) matchEntity.copy(guestPoints = otherPlayerPoints - 1)
                    else matchEntity.copy(hostPoints = otherPlayerPoints - 1)

                    // Mandatory else
                    else -> throw IllegalArgumentException()
                }

                // A
                4 -> addGame(whichPlayer, matchEntity)

                // Mandatory else
                else -> throw IllegalArgumentException()
            }
        }

        private fun addGame(whichPlayer: WhichPlayer, matchEntity: MatchEntity): MatchEntity {
            val updatedEntity = matchEntity.copy(hostPoints = 0, guestPoints = 0)

            if (whichPlayer == WhichPlayer.HOST) {
                updatedEntity.hostGames++
                if (updatedEntity.hostGames == matchEntity.gamesToSet) return addSet(whichPlayer, matchEntity)
            } else {
                updatedEntity.guestGames++
                if (updatedEntity.guestGames == matchEntity.gamesToSet) return addSet(whichPlayer, matchEntity)
            }

            return updatedEntity
        }

        private fun addSet(whichPlayer: WhichPlayer, matchEntity: MatchEntity): MatchEntity {
            val updatedEntity = matchEntity.copy(hostGames = 0, guestGames = 0)

            if (whichPlayer == WhichPlayer.HOST) {
                updatedEntity.hostSets++
                if (updatedEntity.hostSets == matchEntity.setsToMatch) {
                    updatedEntity.winner = 0
                    updatedEntity.ended = Date().time
                }
            } else {
                updatedEntity.guestSets++
                if (updatedEntity.guestSets == matchEntity.setsToMatch) {
                    updatedEntity.winner = 1
                    updatedEntity.ended = Date().time
                }
            }

            return updatedEntity
        }
    }

    enum class WhichPlayer {
        HOST,
        GUEST
    }
}
