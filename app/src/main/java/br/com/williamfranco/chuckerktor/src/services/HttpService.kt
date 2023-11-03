package br.com.williamfranco.chuckerktor.src.services

import android.content.Context
import com.chuckerteam.chucker.api.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.*

class HttpService(context: Context) {
    private val chuckerCollector = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    private val chuckerInterceptor = ChuckerInterceptor.Builder(context)
        .collector(chuckerCollector)
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

    private val okhttpEngine = OkHttp.create {
        addInterceptor(chuckerInterceptor)
    }

    val httpClient = HttpClient(okhttpEngine) {
        expectSuccess = true
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend inline fun <reified T> getData(url: String): T {
        try {
            return httpClient.get(url).body()
        } catch (e: Exception) {
            println("Service error: Failed to load data.")
            throw e
        }
    }
}
