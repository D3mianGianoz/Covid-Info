package pwr.edu.covid.info.ui.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.mukesh.countrypicker.CountryPicker
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
        _binding = FragmentStatisticBinding.inflate(layoutInflater, container, false)

        // Setting observability of the data
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btChangeCountry.setOnClickListener {
            val builder = CountryPicker.Builder().with(requireContext()).listener {
                viewModel.onChangeCountry(it)
            }

            val picker = builder.build();
            picker.showDialog(requireActivity() as AppCompatActivity)
        }

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StatisticViewModel::class.java)

        // Current binding is done in the xml
        binding.viewModel = viewModel

        // It is just a test
        viewModel.networkOperationStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                ServiceStatus.DONE -> {
                    Snackbar.make(requireView(), "Done loading data", Snackbar.LENGTH_SHORT).show()
                }
                ServiceStatus.WAITING -> {
                }
                ServiceStatus.ERROR -> {
                    Snackbar.make(requireView(), "Error loading data", Snackbar.LENGTH_SHORT).show()
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
