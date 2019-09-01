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

    s.append(res.getStringArray(R.array.week_days)[d.dayOfWeek - 1])

    s.append(" ")

    val dayPeriod = res.getStringArray(R.array.day_period)
    s.append(
        when {
            d.hourOfDay < 12 -> dayPeriod[0]
            d.hourOfDay < 19 -> dayPeriod[1]
            else -> dayPeriod[2]
        }
    )

    return s.toString()
}
