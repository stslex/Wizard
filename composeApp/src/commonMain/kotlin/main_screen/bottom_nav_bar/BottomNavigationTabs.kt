package main_screen.bottom_nav_bar

import cafe.adriel.voyager.navigator.tab.Tab

enum class BottomNavigationTabs(
    val tab: Tab
) {
    FILM_FEED(FeedTab),
    MATCH_FEED(MatchFeedTab),
    PROFILE(ProfileTab),
}