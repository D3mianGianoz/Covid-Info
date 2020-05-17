
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    val countryOrRegion: String,
    val county: Any,
    val isoCode: String,
    val lat: Double,
    val long: Double,
    val provinceOrState: Any
)