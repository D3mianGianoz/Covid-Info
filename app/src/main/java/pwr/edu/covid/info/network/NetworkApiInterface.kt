package pwr.edu.covid.info.network

import pwr.edu.covid.info.data.AllCasesGlobal
import retrofit2.Response
import retrofit2.http.GET
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
}