package pwr.edu.covid.info.ui.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import pwr.edu.covid.info.R
import pwr.edu.covid.info.data.newsData.NewsEntity
import pwr.edu.covid.info.data.newsData.NewsListAdapter
import pwr.edu.covid.info.network.ServiceStatus

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private val newsAdapter = NewsListAdapter { news ->
        Toast.makeText(context, news.title, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)



        // TODO: Use the ViewModel
        viewModel.networkOperationStatus.observe(viewLifecycleOwner, Observer {
            when (it){
                ServiceStatus.DONE -> {
                    Toast.makeText(context,"Done loading data", Toast.LENGTH_SHORT).show()
                }
                ServiceStatus.WAITING -> {}
                ServiceStatus.ERROR -> {
                    Toast.makeText(context,"Error loading data", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })
        var newsArray = viewModel.global.value?.news

    }

    private fun showNews(news: List<NewsEntity>) {

        newsAdapter.addItems(news)
    }

}
