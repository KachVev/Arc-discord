package arc.discord

import arc.common.http.AbstractHttpClientModule
import arc.common.module.Module
import org.koin.core.component.inject

class TestModule : Module {
    override val name: String = "Test Module"
    override var isRunning: Boolean = false

    val htppClient by inject<AbstractHttpClientModule>()

    override fun start() {
        println(htppClient.baseUrl)
    }
}