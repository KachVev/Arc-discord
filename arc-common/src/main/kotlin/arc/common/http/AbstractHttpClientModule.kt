package arc.common.http

import arc.common.module.Module
import io.ktor.client.*
import io.ktor.client.statement.*

/**
 * Abstract class representing an HTTP client module.
 * Provides abstract methods for common HTTP operations.
 */
abstract class AbstractHttpClientModule : Module {

    /**
     * The HTTP client instance.
     */
    abstract val client: HttpClient

    /**
     * The base URL for the HTTP client.
     */
    abstract val baseUrl: String

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to. Defaults to "/".
     * @param headers The headers to include in the request. Defaults to an empty map.
     * @param cookies The cookies to include in the request. Defaults to an empty map.
     * @return The HTTP response.
     */
    abstract suspend fun get(
        endpoint: String = "/",
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
    ): HttpResponse

    /**
     * Sends a POST request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to. Defaults to "/".
     * @param headers The headers to include in the request. Defaults to an empty map.
     * @param cookies The cookies to include in the request. Defaults to an empty map.
     * @param body The body of the request. Defaults to null.
     * @return The HTTP response.
     */
    abstract suspend fun post(
        endpoint: String = "/",
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: Any? = null
    ): HttpResponse

    /**
     * Sends a PUT request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to. Defaults to "/".
     * @param headers The headers to include in the request. Defaults to an empty map.
     * @param cookies The cookies to include in the request. Defaults to an empty map.
     * @param body The body of the request. Defaults to null.
     * @return The HTTP response.
     */
    abstract suspend fun put(
        endpoint: String = "/",
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: Any? = null
    ): HttpResponse

    /**
     * Sends a DELETE request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to. Defaults to "/".
     * @param headers The headers to include in the request. Defaults to an empty map.
     * @param cookies The cookies to include in the request. Defaults to an empty map.
     * @return The HTTP response.
     */
    abstract suspend fun delete(
        endpoint: String = "/",
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
    ): HttpResponse

    /**
     * Sends a PATCH request to the specified endpoint.
     *
     * @param endpoint The endpoint to send the request to. Defaults to "/".
     * @param headers The headers to include in the request. Defaults to an empty map.
     * @param cookies The cookies to include in the request. Defaults to an empty map.
     * @param body The body of the request. Defaults to null.
     * @return The HTTP response.
     */
    abstract suspend fun patch(
        endpoint: String = "/",
        headers: Map<String, String> = mapOf(),
        cookies: Map<String, String> = mapOf(),
        body: Any? = null
    ): HttpResponse

    /**
     * Closes the HTTP client.
     */
    open fun close() {
        client.close()
    }
}