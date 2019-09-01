package dev.vitorramos.tennis

import android.content.res.Resources
import org.joda.time.DateTime

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

fun getFormattedDate(res: Resources, date: Long): String {
    val s = StringBuilder()
    val d = DateTime(date)

    s.append(d.dayOfMonth)
    s.append(" ")
    s.append(res.getStringArray(R.array.months)[d.monthOfYear - 1])

    return s.toString()
}
