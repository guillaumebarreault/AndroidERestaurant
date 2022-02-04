package fr.isen.barreault.androiderestaurant

import android.content.Intent
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import fr.isen.barreault.androiderestaurant.basket.BasketActivity
import fr.isen.barreault.androiderestaurant.databinding.ActivityHomeBasketBinding

open class BaseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBasketBinding

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        //setContentView(R.layout.activity_home_basket)
        /* set up: instance of the binding class => use with activity */
        binding = ActivityHomeBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //menuInflater.inflate(R.menu., menu)

        // binding.starterBtn <=> findViewById<Button>(R.id.starterBtn)
        val menuBasket = binding.pictBasket
        val countItem = binding.itemCount.text
        val cnt = 2
        // recuperer valeur item dans le panier
        // cnt = getItemsCount()
        //countItem?.isVisible = cnt>0
        //countItem?.text = cnt.toString()

        menuBasket.setOnClickListener {
            if (cnt > 0){
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }
}