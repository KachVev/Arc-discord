package arc.common.module

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.environmentProperties
import kotlin.reflect.KClass

/**
 * Manages the lifecycle and dependencies of application modules.
 */
class ModulesManager {
    private val modules = hashSetOf<Module>()

    /**
     * The Koin application context.
     */
    val koin = startKoin {
        environmentProperties()
    }.koin

    /**
     * Enables all registered modules and starts the Koin context.
     */
    fun enable() {
        koin.loadModules(listOf(module {
            modules.forEach { module -> single { module } bind module.bind as KClass<Module> }
        }))
        modules.forEach {
            it.start()
            it.isRunning = true
        }
    }

    /**
     * Disables all registered modules and stops the Koin context.
     */
    fun disable() {
        modules.forEach {
            it.stop()
            it.isRunning = false
        }
        stopKoin()
    }

    /**
     * Adds a module to the manager.
     *
     * @param module The module to add.
     */
    fun addModule(vararg module: Module) {
        modules.addAll(module)
    }

    /**
     * Removes a module from the manager.
     *
     * @param module The module to remove.
     */
    fun removeModule(vararg module: Module) {
        modules.removeAll(module)
    }

    /**
     * Starts a specific module and adds it to the Koin context.
     *
     * @param module The module to start.
     */
    fun startModule(module: Module) {
        module.start()
        modules.add(module)
        module.isRunning = true
        koin.loadModules(listOf(module {
            single { module } bind module.bind as KClass<Module>
        }))
    }

    /**
     * Stops a specific module and removes it from the Koin context.
     *
     * @param module The module to stop.
     */
    fun stopModule(module: Module) {
        module.stop()
        module.isRunning = false
        koin.unloadModules(listOf(module {
            single { module } bind module.bind as KClass<Module>
        }))
    }
}