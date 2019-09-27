package dev.vitorramos.tennis.presenter

import android.view.View

data class MatchPresenter(
    val started: String = "",
    val hostName: String = "",
    val hostPoints: String = "",
    val hostGames: String = "",
    val hostSets: String = "",

    val guestName: String = "",
    val guestPoints: String = "",
    val guestGames: String = "",
    val guestSets: String = "",

    val isLast: Boolean = false,

    val onHostClick: (View) -> Unit = {},
    val onGuestClick: (View) -> Unit = {},
    val onDelete: (View) -> Unit = {}
)
