
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsEntity(
    val ampWebUrl: Any,
    val categories: List<String>,
    val cdnAmpWebUrl: Any,
    val excerpt: String,
    val heat: Int,
    val images: List<Image>,
    val locale: String,
    val path: String,
    val provider: Provider,
    val publishedDateTime: String,
    val tags: List<String>,
    val title: String,
    val topics: List<String>,
    val type: String,
    val updatedDateTime: Any,
    val webUrl: String
)