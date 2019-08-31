package dev.vitorramos.tennis

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
