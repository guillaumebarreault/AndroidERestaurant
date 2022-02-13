package fr.isen.barreault.androiderestaurant.dishes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.BaseActivity
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.barreault.androiderestaurant.model.DishModel
import fr.isen.barreault.androiderestaurant.basket.BasketActivity
import fr.isen.barreault.androiderestaurant.model.InBasket
import fr.isen.barreault.androiderestaurant.model.GetPocket
import java.io.File

class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding
    lateinit var sharedPreferences: SharedPreferences
    @SuppressLint("SetTextI18n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences(Constants.APP_PREFS, Context.MODE_PRIVATE)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetails(dish)

        var cnt = 1
        val dishName = (intent.getSerializableExtra("dish") as DishModel)
        val price = (intent.getSerializableExtra("dish") as DishModel).getFormattedPrice()
        val suppBtn = binding.suppBtn
        val addBtn = binding.addBtn
        val totalBtn = binding.totalBtn
        totalBtn.text = "Total : $price €"

        suppBtn.setOnClickListener {
            if (cnt > 1) {
                cnt--
                displayNewQuantity(cnt, totalBtn, price)
            }
        }
        addBtn.setOnClickListener {
            cnt++
            displayNewQuantity(cnt, totalBtn, price)
        }

        totalBtn.setOnClickListener {
            val file = File(cacheDir.absolutePath + Constants.NAME_FILE)
            var data = ArrayList<GetPocket>()

            if (file.exists()) {
                var inBasketNbElement: Int
                Snackbar.make(it, "add to basket", Snackbar.LENGTH_LONG).show()

                if (file.readText().isNotEmpty()) {
                    val recuperate = file.bufferedReader().readText()
                    val result = Gson().fromJson(recuperate, InBasket::class.java)

                    inBasketNbElement = result.quantity

                    for (i in result.items.indices) {
                        addInBasket(
                            GetPocket(result.items[i].dish, result.items[i].quantity),
                            data,
                        )
                    }
                    addInBasket(GetPocket(dishName, cnt), data)
                    inBasketNbElement += cnt

                    val editor = sharedPreferences.edit()
                    editor.putInt(Constants.CNT_BASKET, inBasketNbElement)
                    editor.apply()
                    file.writeText(Gson().toJson(InBasket(data, inBasketNbElement)))
                }
                else {
                    file.writeText(Gson().toJson(
                        InBasket(mutableListOf(GetPocket(dishName, cnt)), 1))
                    )
                    val editor = sharedPreferences.edit()
                    editor.putInt(Constants.CNT_BASKET, 1)
                    editor.apply()
                }
            }
            else {
                file.writeText(Gson().toJson(
                        InBasket(mutableListOf(GetPocket(dishName, cnt)),1))
                )
                val editor = sharedPreferences.edit()
                editor.putInt(Constants.CNT_BASKET, 1)
                editor.apply()
            }

            val intent = Intent(this,BasketActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addInBasket(objectToAdd: GetPocket, data: ArrayList<GetPocket>) {
        var bool = false
        for (i in data.indices)
            if (objectToAdd.dish == data[i].dish) {
                data[i].quantity += objectToAdd.quantity
                bool = true
            }

        if (!bool) {
            data.add(GetPocket(objectToAdd.dish, objectToAdd.quantity))
        }
    }

    private fun displayNewQuantity(cnt: Int, totalButton: Button, price: String) {
        binding.cntQuantity.text = cnt.toString()
        totalButton.text = "Total: ${(price.toFloat()*cnt)} €"
    }

    private fun initDetails(dish: DishModel) {
        binding.carrousel.adapter = PagerAdapter(this, dish.pictures)
        binding.nameDetails.text = dish.name_fr
        binding.descriptionDetails.text = dish.ingredients.joinToString(", ") { it.name_fr }
    }

}