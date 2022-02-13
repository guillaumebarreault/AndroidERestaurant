package fr.isen.barreault.androiderestaurant.basket

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.barreault.androiderestaurant.BaseActivity
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.HomeActivity
import fr.isen.barreault.androiderestaurant.connexion.ConnexionActivity
import fr.isen.barreault.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.barreault.androiderestaurant.model.InBasket
import fr.isen.barreault.androiderestaurant.model.GetPocket
import java.io.File

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIfConnect()

        val file = File(cacheDir.absolutePath + Constants.NAME_FILE)
        if (file.exists()) {
            val recup = file.readText()
            val resultat = Gson().fromJson(recup, InBasket::class.java)
            Log.d("panier", recup)
            val data = ArrayList<GetPocket>()

            for (j in resultat.items.indices) {
                data.add(GetPocket(resultat.items[j].dish, resultat.items[j].quantity))
            }
            displayBasket(InBasket(data, resultat.quantity))
        }

        val addDishBtn = binding.addDishesBtn
        val orderBtn = binding.orderBtn

        addDishBtn.setOnClickListener {
            val home = Intent(this, HomeActivity::class.java)
            startActivity(home)
        }
        orderBtn.setOnClickListener {
            if(binding.orderBtn.text == "Commander") {
                val order = Intent(this, OrderActivity::class.java)
                startActivity(order)
            }
            else {
                val connect = Intent(this, ConnexionActivity::class.java)
                startActivity(connect)
            }
        }
    }

    private fun displayBasket(item: InBasket) {
        val recyclerview = binding.dishListPocket
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = BasketAdapter(item.items) {
            item.items.remove(it)
            updateBasket(item)
            invalidateOptionsMenu()
        }
    }

    private fun updateBasket(basket: InBasket) {
        val file = File(cacheDir.absolutePath + Constants.NAME_FILE)
        dishCountInPref(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun dishCountInPref(basket: InBasket) {
        val count = basket.items.sumOf { it.quantity }
        basket.quantity = count
        val editor = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE).edit()
        editor.putInt(Constants.CNT_BASKET, count)
        editor.apply()
    }

    private fun checkIfConnect() {
        val userIdSave =
            getSharedPreferences(Constants.APP_PREFS, MODE_PRIVATE).contains(Constants.USER_ID)
        if (userIdSave) {
            binding.orderBtn.text = "Commander"
        }
    }

}