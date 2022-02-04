package fr.isen.barreault.androiderestaurant.basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.barreault.androiderestaurant.model.GetPocket
import java.io.File


class BasketActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBasketBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityBasketBinding.inflate(layoutInflater)
            setContentView(binding.root)


            loadBasket()
        }

        private fun loadBasket() {
            val filename = "myPocket.json"
            val items = intent.getStringExtra("dataPocket")
            val recyclerview = binding.dishListPocket
            recyclerview.layoutManager = LinearLayoutManager(this)
            //recyclerview.adapter = BasketAdapter(items)
    }
}