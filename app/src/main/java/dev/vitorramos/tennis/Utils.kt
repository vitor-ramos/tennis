package dev.vitorramos.tennis

const val PREF_FIELD_MATCH_ID = "matchId"
const val PREF_FILE_NAME = "tennis"

fun getFormattedPoints(points: Int): String {
    return when (points) {
        0 -> "0"
        1 -> "15"
        2 -> "30"
        3 -> "40"
        4 -> "A"
        else -> ""
    }
}