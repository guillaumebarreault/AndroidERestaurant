package fr.isen.barreault.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.barreault.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.barreault.androiderestaurant.dishes.DishesActivity


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonStarter = binding.starterBtn
        val buttonDish = binding.dishBtn
        val buttonDessert = binding.dessertBtn

        buttonStarter.setOnClickListener {
            selectCategory(getString(R.string.home_starters))
        }
        buttonDish.setOnClickListener {
            selectCategory(getString(R.string.home_dishes))
        }
        buttonDessert.setOnClickListener {
            selectCategory(getString(R.string.home_desserts))
        }
    }
    private fun selectCategory(category: String) {
        //Toast.makeText(this, "Selection des entrées.", Toast.LENGTH_LONG).show()
        val changePage: Intent = Intent(this, DishesActivity::class.java)
        /* put string in new activity */
        changePage.putExtra("category_type", category)
        Log.i("INFO","End of HomeActivity")
        startActivity(changePage)
        //finish()    /* kill activity */
    }
}