package fr.isen.barreault.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.databinding.ActivityDishBinding
import fr.isen.barreault.androiderestaurant.model.DishModel
import fr.isen.barreault.androiderestaurant.model.DishResult
import org.json.JSONObject


class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val categoryType: String = intent.getStringExtra("category_type") ?: ""
        binding.categoryTitle.text = categoryType

        loadDishes(categoryType)

        /* val dishes : List<ItemDishModel>
        if(category=="Entrées") {
            dishes = listOf(
                ItemDishModel("Fondu Savoyarde", R.drawable.pict_fondu,"14€"),
                ItemDishModel("Poulet Curry", R.drawable.pict_pouletcurry,"11€"))
        }
        else if(category=="Plats") {
            dishes = listOf(
                ItemDishModel("Lasagne", R.drawable.pict_lasagne, "13€"),
                ItemDishModel("Tartiflette", R.drawable.pict_tartiflette, "12€"))
        }
        else {
            dishes = listOf(
                ItemDishModel("Crêpe au Caramel", R.drawable.pict_crepe, "4.5€"),
                ItemDishModel("Tiramisu", R.drawable.pict_tiramisu, "5€"))
        } */
    }

    private fun loadDishes(category: String){
        /* http request to the API */
        //val zoneRep: TextView = binding.httpResp
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")

        /* request string response from provided url */
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                val dishresult = Gson().fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishresult.data.firstOrNull {it.name_fr==category}?.items ?: listOf())
                //zoneRep.text = "response: ${dishResult.data[1].items[0].name_fr}"
                Log.d("", "response: $dishresult")
            },{
                //zoneRep.text = "Volley error: $it"
                Log.e("DishActivity", "error recup list plats")
            })
        queue.add(jsonRequest)
    }

    private fun displayDishes(dishes: List<DishModel>) {
        /* getting the recyclerview by its id */
        val recyclerview = binding.dishList
        /* this creates a vertical layout Manager */
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = DishAdapter(dishes){
            val selectedDish = Intent( this, DetailsActivity::class.java)
            selectedDish.putExtra("dish", it)
            Log.i("INFO","End of DishActivity")
            startActivity(selectedDish)
        }


    }
}




