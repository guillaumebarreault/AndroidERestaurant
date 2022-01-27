package fr.isen.barreault.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.barreault.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        /*set up: instance of the binding class => use with activity */
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* declare value take id button */
        val buttonStarter = binding.starterBtn
        val buttonDish = binding.dishBtn
        val buttonDessert = binding.dessertBtn
        // binding.starterBtn <=> findViewById<Button>(R.id.starterBtn)

        /* call function => click on button */
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
        /* intent: passive data structure holding an abstract description of an action to be performed. */
        val changePage: Intent = Intent(this@HomeActivity, DishActivity::class.java)
        /* put string in new activity */
        changePage.putExtra("category_type", "Voici nos $category :")
        Log.i("INFO","End of HomeActivity")
        startActivity(changePage)
        //finish()    /* kill activity */
    }
}