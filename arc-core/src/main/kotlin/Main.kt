package arc.discord

import arc.common.http.DefaultHttpClientModule
import arc.common.module.ModulesManager

suspend fun main() {
    val modulesManager = ModulesManager()

    modulesManager.addModule(
        DefaultHttpClientModule("https://funpay.com"),
        TestModule()
    )
    modulesManager.enable()
}