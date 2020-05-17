
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsApiResponse(
    val location: Location,
    val news: List<NewsEntity>,
    val updatedDateTime: String
)