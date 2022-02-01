package fr.isen.barreault.androiderestaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private lateinit var binding: FirstFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FirstFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupData()
    }

    private fun setupData() {
        binding.txtMain.text = getString(R.string.first_fragment)
        binding.imgMain.setImageResource(R.mipmap.ic_launcher)
    }
}
