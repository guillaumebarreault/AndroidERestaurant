package fr.isen.barreault.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import fr.isen.barreault.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.barreault.androiderestaurant.dishes.DishesActivity

class HomeActivity : BaseActivity() {

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
        //Toast.makeText(this, "Selection des plats.", Toast.LENGTH_LONG).show()
        val moveCategory: Intent = Intent(this, DishesActivity::class.java)
        /* put string in new activity */
        moveCategory.putExtra("category_type", category)
        //Log.i("INFO","End of HomeActivity")
        startActivity(moveCategory)
        //finish()    /* kill activity */
    }
}