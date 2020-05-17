package pwr.edu.covid.info.data.statsData

import com.squareup.moshi.JsonClass


/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */

/**
 * JwtToken example
 */
@JsonClass(generateAdapter = true)
data class JwtResponse(
    val token: String,
    val type: String = "Bearer"
)

/**
 * Worldwide stats: cases, deaths, recovered, time last updated, and active cases. Data is updated every 10 minutes.
 */
@JsonClass(generateAdapter = true)
data class AllCasesGlobal(
    val updated: Long,
    val cases: Long,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int,
    val casesPerOneMillion: Float,
    val deathsPerOneMillion: Float,
    val tests: Int,
    val testsPerOneMillion: Double,
    val affectedCountries: Int
)

fun AllCasesGlobal.asDomainObject(): CovidGlobal {
    return CovidGlobal(
        updated,
        cases,
        deaths,
        recovered,
        active,
        critical
    )
}

