package pwr.edu.covid.info.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pwr.edu.covid.info.data.newsData.NewsAdapter
import pwr.edu.covid.info.data.newsData.NewsItem
import pwr.edu.covid.info.data.newsData.NewsListener
import pwr.edu.covid.info.databinding.FragmentNewsBinding
import pwr.edu.covid.info.network.ServiceStatus

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var viewModelAdapter: NewsAdapter
    private var _binding: FragmentNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        //On click on single item (View Lesson 7)
        viewModelAdapter = NewsAdapter(NewsListener {
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show()
        })

        // Setting observability of the data
        binding.lifecycleOwner = viewLifecycleOwner

        // Set up the recyclerview
        binding.newsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.newsList.observe(viewLifecycleOwner, Observer<List<NewsItem>> { news ->
            news?.apply {
                viewModelAdapter.submitList(news)
            }
        })

        // TODO: Use the ViewModel
        viewModel.networkOperationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                ServiceStatus.DONE -> {
                    Toast.makeText(context, "Done loading data", Toast.LENGTH_SHORT).show()
                }
                ServiceStatus.WAITING -> {
                }
                ServiceStatus.ERROR -> {
                    Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
