package br.com.williamfranco.chuckerktor.src.common.services

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpService(context: Context) {

    private val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )

    private val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(250_000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

    private val okhttpEngine = OkHttp.create {
        addInterceptor(chuckerInterceptor)
    }

    val client = HttpClient(okhttpEngine) {
        expectSuccess = true
        install(HttpTimeout) {
            connectTimeoutMillis = 5_000
            requestTimeoutMillis = 5_000
            socketTimeoutMillis = 5_000
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                },
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }

    suspend inline fun <reified T> getData(url: String): T = client.get(url).body()
}
