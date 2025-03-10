package arc.common.module

import org.koin.core.component.KoinComponent
import kotlin.reflect.KClass

/**
 * Interface representing a module in the application.
 * Implements KoinComponent for dependency injection.
 */
interface Module : KoinComponent {

    /**
     * The name of the module.
     */
    val name: String

    /**
     * Indicates whether the module is running.
     */
    var isRunning: Boolean

    /**
     * The class to bind the module to in Koin.
     */
    val bind: KClass<*>
        get() = this::class

    /**
     * Configures the module.
     * This method should be overridden to set up the module's configuration.
     *
     * The default implementation is empty to allow optional overriding.
     */
    fun configure() = Unit

    /**
     * Starts the module.
     * This method should be overridden to define the module's startup behavior.
     *
     * The default implementation is empty because the module may not require
     * explicit startup logic, and this prevents forcing all implementations
     * to provide an unnecessary override.
     */
    fun start() = Unit

    /**
     * Stops the module.
     * This method should be overridden to define the module's shutdown behavior.
     *
     * The default implementation is empty for the same reason as `start()`.
     * Some modules may not need explicit shutdown logic.
     */
    fun stop() = Unit
}