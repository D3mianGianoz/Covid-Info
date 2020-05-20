package pwr.edu.covid.info.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 *  Since we only have one service, this can all go in one file.
 *  If you add more services, split this to multiple files and make sure to share the retrofit
 *  object between services.
 */

private const val baseUrl = "https://corona-virus-stats.herokuapp.com/api/v1/"
private const val novelUrl = "https://corona.lmao.ninja/v2/"
private const val newsUrl = "https://api.smartable.ai/coronavirus/"


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Build the factory
 */
private val converterFactory = MoshiConverterFactory.create(moshi)

private val callAdapterFactory = CoroutineCallAdapterFactory()

private val loggingInterceptor: HttpLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val client = OkHttpClient()
    .newBuilder()
    .addInterceptor(loggingInterceptor)
    .build()

private val newsClient = OkHttpClient()
    .newBuilder()
    .addInterceptor(Interceptor { chain ->
        val builder = chain.request().newBuilder()
        builder.header("Subscription-Key", "f3a9a5a176094d65b10f54b92b35ef87") //3009d4ccc29e4808af1ccc25c69b4d5d
        return@Interceptor chain.proceed(builder.build())
    })
    .addInterceptor(loggingInterceptor)
    .build()

/**
 * Main entry point for network access without authentication
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(novelUrl)
        .client(client)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    //.addConverterFactory(ScalarsConverterFactory.create())

    val statisticInterface: StatisticInterface =
        retrofit.create(StatisticInterface::class.java)

    val covidInterface: NovelCOVIDInterface =
        retrofit.create(NovelCOVIDInterface::class.java)

    private val newsRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(newsUrl)
        .client(newsClient)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    val newsInterface: NewsInterface =
        newsRetrofit.create(NewsInterface::class.java)
}


enum class ServiceStatus {
    WAITING,
    DONE,
    ERROR
}