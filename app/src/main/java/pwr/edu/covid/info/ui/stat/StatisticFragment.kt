package pwr.edu.covid.info.ui.stat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import pwr.edu.covid.info.R
import pwr.edu.covid.info.databinding.FragmentStatisticBinding
import pwr.edu.covid.info.network.ServiceStatus

class StatisticFragment : Fragment() {

    private lateinit var viewModel: StatisticViewModel

    private var _binding: FragmentStatisticBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticBinding.inflate(layoutInflater,container, false)

        // Setting observability of the data
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticViewModel::class.java)

        // Current binding is done in the xml
        binding.viewModel = viewModel

        // It is just a test
        viewModel.networkOperationStatus.observe(viewLifecycleOwner, Observer {
            when (it){
                ServiceStatus.DONE -> {
                    Toast.makeText(context,"Done loading data",Toast.LENGTH_SHORT).show()
                }
                ServiceStatus.WAITING -> {}
                ServiceStatus.ERROR -> {
                    Toast.makeText(context,"Error loading data",Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
