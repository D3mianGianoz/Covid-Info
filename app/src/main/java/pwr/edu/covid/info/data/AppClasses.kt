package pwr.edu.covid.info.data

/**
 * Worldwide stats: cases, deaths, recovered, time last updated, and active cases
 */
data class CovidGlobal (
    val updated : Long,
    val cases : Long,
    val deaths : Int,
    val recovered : Int,
    val active : Int,
    val critical : Int
)