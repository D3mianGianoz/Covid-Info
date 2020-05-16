package pwr.edu.covid.info.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pwr.edu.covid.info.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        // In this variabile we have all the reference of the view contained in the @[fragment_home.xml].
        // We can access directly just like a normal object, here lets see an example
        binding.btnCall.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:800 190 590")
            startActivity(intent)
        }

        binding.btnOnlinehelp.setOnClickListener {
            val url = "https://www.practo.com/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        binding.btnSymptoms.setOnClickListener {
            val url = "https://www.cdc.gov/coronavirus/2019-ncov/symptoms-testing/symptoms.html"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
