package fr.isen.barreault.androiderestaurant.dishes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.FragmentPictDetailsBinding

class PictDetailsFragment : Fragment() {

    private lateinit var binding: FragmentPictDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPictDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("picture_url",)?.let { pictureUrl ->
        /* null case of picture url */
        if (pictureUrl == "") {
            binding.pictureFrag.setImageResource(R.drawable.logo_chef)
        }
        else {
            Picasso.get()
                .load(pictureUrl)
                .placeholder(R.drawable.logo_chef)
                .error(R.drawable.logo_chef)
                .into(binding.pictureFrag)
        }
        }
    }

    companion object {
        fun newInstance(pictureUrl: String) = PictDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString("picture_url", pictureUrl)
                }
        }
    }

}
