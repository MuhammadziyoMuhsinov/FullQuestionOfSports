package uz.muhammadziyo.fullquestionofsports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.muhammadziyo.fullquestionofsports.databinding.FragmentMainBinding


class FragmentMain : Fragment() {

    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        binding.cardTennis.setOnClickListener {
            findNavController().navigate(R.id.fragmentTennis)
        }
        binding.cardVolleyball.setOnClickListener {
            findNavController().navigate(R.id.fragmentVolleyball)
        }
        binding.cardBasketball.setOnClickListener {
            findNavController().navigate(R.id.fragmentBasketball)
        }

        return binding.root
    }


}