package dev.vitorramos.tennis.presenter

class MatchPresenter(
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

    private val onHostClick: () -> Unit = {},
    private val onGuestClick: () -> Unit = {},
    private val onDeleteClick: () -> Unit = {}
) {
    fun onClickHost() = onHostClick()
    fun onClickGuest() = onGuestClick()
    fun onClickDelete() = onDeleteClick()
}
