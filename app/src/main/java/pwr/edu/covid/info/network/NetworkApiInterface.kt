package pwr.edu.covid.info.network

import retrofit2.Response
import retrofit2.http.GET
import java.util.*

/**
 * Just an example of interface from the postman api
 */
interface StatisticInterface{
    @GET("cases/general-stats")
    suspend fun getGeneralStats(): Response<Objects>
}