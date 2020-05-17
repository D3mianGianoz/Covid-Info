
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val attribution: Any,
    val height: Int,
    val title: String,
    val url: String,
    val width: Int
)