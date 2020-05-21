package pwr.edu.covid.info.data.statsData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryCases(
    val active: Int,
    val cases: Long,
    val casesPerOneMillion: Float,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val deathsPerOneMillion: Float,
    val recovered: Int,
    val tests: Int,
    val testsPerOneMillion: Float,
    val todayCases: Int,
    val todayDeaths: Int,
    val updated: Long
)

fun CountryCases.asDomainObject(): CovidGlobal{
    return CovidGlobal(
        updated,
        cases,
        deaths,
        recovered,
        active,
        critical
    )
}

@JsonClass(generateAdapter = true)
data class CountryInfo(
    val _id: Int,
    val flag: String,
    val iso2: String,
    val iso3: String,
    val lat: Double,
    val long: Double
)