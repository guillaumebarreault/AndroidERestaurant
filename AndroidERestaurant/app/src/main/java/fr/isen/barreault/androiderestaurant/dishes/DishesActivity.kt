package fr.isen.barreault.androiderestaurant.dishes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.BaseActivity
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.databinding.ActivityDishesBinding
import fr.isen.barreault.androiderestaurant.model.DishModel
import fr.isen.barreault.androiderestaurant.model.DishResult
import org.json.JSONObject

class DishesActivity : BaseActivity() {

    private lateinit var binding: ActivityDishesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryType: String = intent.getStringExtra("category_type") ?: ""
        binding.categoryTitle.text = categoryType

        loadDishes(categoryType)
    }

    private fun loadDishes(category: String){
        /* http request to the API */
        //val zoneRep: TextView = binding.httpResp
        val queue = Volley.newRequestQueue(this)
        val jsonObject = JSONObject()

        jsonObject.put(Constants.ID_SHOP,Constants.KEY_SHOP)

        /* request string response from provided url */
        val jsonRequestMenu = JsonObjectRequest(
            Request.Method.POST, Constants.URL_MENU, jsonObject,
            { response ->
                val dishResult = Gson().fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishResult.data.firstOrNull {it.name_fr == category}?.items ?: listOf())
                //zoneRep.text = "response: ${dishResult.data[1].items[0].name_fr}"
                Log.i("INFO", "response: $dishResult")
            },{
                //zoneRep.text = "Volley error: $it"
                Log.e("ERROR", "error get dishes list")
            })
        jsonRequestMenu.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,0,1f)
        queue.add(jsonRequestMenu)
    }

    private fun displayDishes(dishes: List<DishModel>) {
        /* getting the recyclerview by its id */
        val recyclerview = binding.dishList
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = DishesAdapter(dishes){
            val selectedDish = Intent( this, DetailsActivity::class.java)
            selectedDish.putExtra("dish", it)
            Log.i("INFO","End of DishesActivity")
            startActivity(selectedDish)
        }
    }

}




