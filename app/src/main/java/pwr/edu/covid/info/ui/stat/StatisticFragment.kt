package pwr.edu.covid.info.ui.stat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticViewModel::class.java)

        // Actual binding
        viewModel.global.value?.apply {
            binding.apply {
                textNumAffected.text = cases.toString().format("%03d", 1)
                textNumDeath.text = deaths.toString()
                textNumActive.text = active.toString()
                textNumRecovered.text = recovered.toString()
                textNumCritical.text = critical.toString()
            }
        }

        // It is just a test
        viewModel.networkOperationStatus.observe(viewLifecycleOwner, Observer {
            when (it){
                ServiceStatus.DONE -> {
                    // Actual binding
                    viewModel.global.value?.apply {
                        binding.apply {
                            textNumAffected.text = cases.toString().format("%03d", 1)
                            textNumDeath.text = deaths.toString()
                            textNumActive.text = active.toString()
                            textNumRecovered.text = recovered.toString()
                            textNumCritical.text = critical.toString()
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
