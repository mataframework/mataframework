package uitests

import app.App
import app.AppLauncher
import org.junit.jupiter.api.Test

class MainScreenTest {
    private var app: App? = null

    @Test
    fun checkOpenApp() {
        app = AppLauncher().launch()
    }
}