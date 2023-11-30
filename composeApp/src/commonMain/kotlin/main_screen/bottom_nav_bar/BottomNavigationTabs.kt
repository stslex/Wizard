package main_screen.bottom_nav_bar

import cafe.adriel.voyager.navigator.tab.Tab

enum class BottomNavigationTabs(
    val tab: Tab
) {
    FEED(FeedTab),
    MATCH_FEED(MatchFeedTab),
    PROFILE(ProfileTab),
}