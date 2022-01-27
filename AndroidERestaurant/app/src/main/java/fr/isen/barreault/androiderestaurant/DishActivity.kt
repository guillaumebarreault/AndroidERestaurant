package fr.isen.barreault.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.barreault.androiderestaurant.databinding.ActivityDishBinding


class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* get String category from HomeActivity */
        val category = intent.getStringExtra("category_type")
        findViewById<TextView>(R.id.categoryTitle).text = category

        /* create detailed list of dishes */
        val dishes = listOf(
            ItemDishModel("Fondu Savoyarde", R.drawable.image_menu_home,"14€"),
            ItemDishModel("Poulet Curry", R.drawable.image_menu_home,"11€"),
            ItemDishModel("Lasagne", R.drawable.image_menu_home,"13€"),
            ItemDishModel("Tartiflette", R.drawable.image_menu_home,"12€"),
            ItemDishModel("Moelleux au Chocolat avec Crème Anglaise", R.drawable.image_menu_home,"6€"),
            ItemDishModel("Crêpe au Caramel Beurre Salé", R.drawable.image_menu_home,"4.5€"),
            ItemDishModel("Tiramisu", R.drawable.image_menu_home,"5€"),
        )

        /* getting the recyclerview by its id */
        val recyclerview = findViewById<RecyclerView>(R.id.dishList)
        /* this creates a vertical layout Manager */
        recyclerview.layoutManager = LinearLayoutManager(this)

        /* Setting the Adapter with the recyclerview */
        recyclerview.adapter = DishAdapter(dishes){
            val selectItem = Intent( this@DishActivity, DetailsActivity::class.java)
            selectItem.putExtra("item_list", category)
            Log.i("INFO","End of DishActivity")
            startActivity(selectItem)
        }

    }
}

