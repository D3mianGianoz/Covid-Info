package pwr.edu.covid.info.network


import pwr.edu.covid.info.data.newsData.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*


/**
 * All cases updated every 10 minutes
 */
interface NewsInterface {
    @GET("news/global")
    suspend fun getNews(): Response<NewsApiResponse>
}