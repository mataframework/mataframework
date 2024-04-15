package pages.scroll

import java.time.Duration

data class ScrollAction(
        val scrollAmount: Int,
        val scrollDirection: ScrollDirection = ScrollDirection.DOWN,
        val scrollTimes: Int = 1,
        val scrollDuration: Duration = Duration.ofSeconds(1)
)
