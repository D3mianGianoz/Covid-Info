package pwr.edu.covid.info.ui.stat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mukesh.countrypicker.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import pwr.edu.covid.info.data.statsData.AllCasesGlobal
import pwr.edu.covid.info.data.statsData.CountryCases
import pwr.edu.covid.info.data.statsData.CovidGlobal
import pwr.edu.covid.info.data.statsData.asDomainObject
import pwr.edu.covid.info.network.Network
import pwr.edu.covid.info.network.ServiceStatus
import timber.log.Timber

class StatisticViewModel : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    /**
     * Tracking the status of the networkOperation
     */
    private val _networkOperationStatus = MutableLiveData<ServiceStatus>()
    val networkOperationStatus: LiveData<ServiceStatus>
        get() = _networkOperationStatus

    private fun networkOperationSuccess() {
        _networkOperationStatus.postValue(ServiceStatus.DONE)
    }

    private fun networkOperationError() {
        _networkOperationStatus.postValue(ServiceStatus.ERROR)
    }

    private val _global = MutableLiveData<CovidGlobal>()
    val global: LiveData<CovidGlobal>
        get() = _global

    private val _countrySelected = MutableLiveData<String>()
    val countrySelected: LiveData<String>
        get() = _countrySelected

    init {
        getGlobalStatsFromNetwork()
        _global.value =
            CovidGlobal(0, 0, 0, 0, 0, 0)
        _countrySelected.value = "Global"
    }

    private fun getGlobalStatsFromNetwork() = viewModelScope.launch {
        _networkOperationStatus.postValue(ServiceStatus.WAITING)
        try {
            val dataFromServer = Network.covidInterface.getGlobalStats(false)

            if (dataFromServer.isSuccessful) {
                val body: AllCasesGlobal = dataFromServer.body()!!

                // Store result
                _global.postValue(body.asDomainObject())

                Timber.e("New Global stats: ${_global.value}")

                // Success
                networkOperationSuccess()
            } else {
                Timber.e("Error in the request: ${dataFromServer.errorBody().toString()}")

                //Stop Spinner
                networkOperationError()
            }
        } catch (ex: Exception) {
            when (ex) {
                is NullPointerException -> Timber.e("Error during data conversion ${ex.message}")
                else -> Timber.e("Generic error ${ex.message}")
            }

            //Stop Spinner
            networkOperationError()
        }
    }

    fun onChangeCountry(country: Country) = viewModelScope.launch {
        _networkOperationStatus.postValue(ServiceStatus.WAITING)

        // Update the UI
        _countrySelected.postValue(country.name)

        try {
            val dataFromServer =
                Network.covidInterface.getSpecifCountry(
                    country = country.code,
                    yesterday = false,
                    strict = true
                )

            if (dataFromServer.isSuccessful) {
                val body: CountryCases = dataFromServer.body()!!

                // Store result
                _global.postValue(body.asDomainObject())

                Timber.e("New Global stats: ${_global.value}")

                // Success
                networkOperationSuccess()
            } else {
                Timber.e("Error in the request: ${dataFromServer.errorBody().toString()}")

                //Stop Spinner
                networkOperationError()
            }
        } catch (ex: Exception) {
            when (ex) {
                is NullPointerException -> Timber.e("Error during data conversion ${ex.message}")
                else -> Timber.e("Generic error ${ex.message}")
            }

            //Stop Spinner
            networkOperationError()
        }
    }

    /**
     * When we clear the viewModel we need also to cancel the Job and not let it pending
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
