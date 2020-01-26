package dev.vitorramos.tennis

import android.content.res.Resources
import org.joda.time.DateTime

fun getFormattedPoints(points: Int) = when (points) {
    0 -> "0"
    1 -> "15"
    2 -> "30"
    3 -> "40"
    4 -> "A"
    else -> ""
}

fun getFormattedDate(res: Resources, date: Long) = with(DateTime(date)) {
    "$dayOfMonth ${res.getStringArray(R.array.months)[monthOfYear - 1]}"
}
