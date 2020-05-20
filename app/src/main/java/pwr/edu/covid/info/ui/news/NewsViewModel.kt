package pwr.edu.covid.info.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import pwr.edu.covid.info.data.newsData.NewsApiResponse
import pwr.edu.covid.info.data.newsData.NewsEntity
import pwr.edu.covid.info.data.newsData.NewsItem
import pwr.edu.covid.info.data.newsData.asDomainObject
import pwr.edu.covid.info.network.Network
import pwr.edu.covid.info.network.ServiceStatus
import timber.log.Timber

class NewsViewModel : ViewModel() {

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

    private val _global = MutableLiveData<NewsApiResponse>()
    val global: LiveData<NewsApiResponse>
        get() = _global

    /**
     * A playlist of news that can be shown on the screen. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private val _newsList = MutableLiveData<List<NewsItem>>()

    /**
     * A playlist of news that can be shown on the screen. Views should use this to get access
     * to the data.
     */
    val newsList: LiveData<List<NewsItem>>
        get() = _newsList


    init {
        getGlobalNewsFromNetwork()
        _global.value =
            NewsApiResponse(null, ArrayList(), null)
    }

    private fun getGlobalNewsFromNetwork() = viewModelScope.launch {
        _networkOperationStatus.postValue(ServiceStatus.WAITING)
        try {
            val dataFromServer = Network.newsInterface.getNews()

            if (dataFromServer.isSuccessful) {
                val body: NewsApiResponse = dataFromServer.body()!!

                // Store result
                _global.postValue(body)

                Timber.d("New Global news: ${_global.value}")

                val news: List<NewsItem> = global.value!!.news?.asDomainObject()!!

                _newsList.postValue(news)
                Timber.i("lista: $newsList")

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
