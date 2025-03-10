package arc.common.http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

class DefaultHttpClientModule(override val baseUrl: String) : AbstractHttpClientModule() {
    override val client: HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override val name: String = "DefaultHttpClientModule"
    override val bind: KClass<*>
        get() = AbstractHttpClientModule::class
    override var isRunning: Boolean = false

    override suspend fun get(
        endpoint: String,
        headers: Map<String, String>,
        cookies: Map<String, String>,
    ): HttpResponse {
        return client.get {
            url {
                takeFrom(baseUrl)
                encodedPath += endpoint
            }
            headers.forEach { (key, value) -> header(key, value) }
            cookies.forEach { (key, value) -> cookie(key, value) }
        }
    }

    override suspend fun post(
        endpoint: String,
        headers: Map<String, String>,
        cookies: Map<String, String>,
        body: Any?
    ): HttpResponse {
        return client.post {
            url(baseUrl + endpoint)
            headers.forEach { (key, value) -> header(key, value) }
            cookies.forEach { (key, value) -> cookie(key, value) }
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }

    override suspend fun put(
        endpoint: String,
        headers: Map<String, String>,
        cookies: Map<String, String>,
        body: Any?
    ): HttpResponse {
        return client.put {
            url(baseUrl + endpoint)
            headers.forEach { (key, value) -> header(key, value) }
            cookies.forEach { (key, value) -> cookie(key, value) }
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }

    override suspend fun delete(
        endpoint: String,
        headers: Map<String, String>,
        cookies: Map<String, String>
    ): HttpResponse {
        return client.delete {
            url {
                takeFrom(baseUrl)
                encodedPath += endpoint
            }
            headers.forEach { (key, value) -> header(key, value) }
            cookies.forEach { (key, value) -> cookie(key, value) }
        }
    }

    override suspend fun patch(
        endpoint: String,
        headers: Map<String, String>,
        cookies: Map<String, String>,
        body: Any?
    ): HttpResponse {
        return client.patch {
            url(baseUrl + endpoint)
            headers.forEach { (key, value) -> header(key, value) }
            cookies.forEach { (key, value) -> cookie(key, value) }
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }
}
