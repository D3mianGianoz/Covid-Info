package pwr.edu.covid.info.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
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

/**
 * Main entry point for network access without authentication
 */
object Network {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
        .build()

    val statisticInterface: StatisticInterface =
        retrofit.create(StatisticInterface::class.java)

}