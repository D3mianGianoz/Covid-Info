package pwr.edu.covid.info.data.newsData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val attribution: Any?,
    val height: Int?,
    val title: String,
    val url: String,
    val width: Int?
)

@JsonClass(generateAdapter = true)
data class Provider(
    val authors: Any?,
    val domain: String?,
    val images: Any?,
    val name: String?,
    val publishers: Any?
)

@JsonClass(generateAdapter = true)
data class NewsEntity(
    val ampWebUrl: Any?,
    val categories: List<String>?,
    val cdnAmpWebUrl: Any?,
    val excerpt: String,
    val heat: Int?,
    val images: List<Image?>?,
    val locale: String?,
    val path: String?,
    val provider: Provider?,
    val publishedDateTime: String?,
    val tags: List<String>?,
    val title: String,
    val topics: List<String>,
    val type: String?,
    val updatedDateTime: Any?,
    val webUrl: String
)

fun List<NewsEntity>.asDomainObject(): List<NewsItem>{
    return map {
        NewsItem(
            title = it.title,
            image = it.images?.get(0),
            description = it.excerpt,
            webUrl = it.webUrl
        )
    }
}

@JsonClass(generateAdapter = true)
data class NewsApiResponse(
    val location: Location?,
    val news: List<NewsEntity>,
    val updatedDateTime: String?
)

@JsonClass(generateAdapter = true)
data class Location(
    val countryOrRegion: String?,
    val county: Any?,
    val isoCode: String?,
    val lat: Double?,
    val long: Double?,
    val provinceOrState: Any?
)