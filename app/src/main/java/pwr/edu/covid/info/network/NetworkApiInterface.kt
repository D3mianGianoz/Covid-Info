package pwr.edu.covid.info.network

import pwr.edu.covid.info.data.statsData.AllCasesGlobal
import pwr.edu.covid.info.data.statsData.CountryCases
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 * Just an example of interface from the postman api
 */
interface StatisticInterface {
    @GET("cases/general-stats")
    suspend fun getGeneralStats(): Response<Objects>
}

/**
 * All cases updated every 10 minutes
 */
interface NovelCOVIDInterface {
    @GET("all")
    suspend fun getGlobalStats(@Query("yesterday") yesterday: Boolean): Response<AllCasesGlobal>

    @GET("countries/{query}")
    suspend fun getSpecifCountry(@Path("query") country: String,
                                 @Query("yesterday") yesterday: Boolean?,
                                 @Query("strict") strict: Boolean?): Response<CountryCases>
}