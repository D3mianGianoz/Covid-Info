
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Provider(
    val authors: Any,
    val domain: String,
    val images: Any,
    val name: String,
    val publishers: Any
)