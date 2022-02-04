package fr.isen.barreault.androiderestaurant.dishes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import fr.isen.barreault.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.barreault.androiderestaurant.model.DishModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.basket.BasketActivity
import fr.isen.barreault.androiderestaurant.connexion.ConnexionActivity
import fr.isen.barreault.androiderestaurant.model.GetPocket
import java.io.File


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    @SuppressLint("ResourceType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel

        //val data = ArrayList<String>(GetInPocket)

        setupViewPager(dish)

        var cnt = 1
        val price = (intent.getSerializableExtra("dish") as DishModel).getFormattedPrice()
        val suppBtn = binding.suppBtn
        val addBtn = binding.addBtn
        val totalButton = binding.totalBtn
        totalButton.text = "Total: $price €"

        suppBtn.setOnClickListener {
            if (cnt > 1) {
                cnt--
                binding.cntDetails.text = cnt.toString()
                totalButton.text = "Total: ${(price.toFloat()*cnt).toString()} €"
            }
        }
        addBtn.setOnClickListener {
            cnt++
            binding.cntDetails.text = cnt.toString()
            totalButton.text = "Total: ${(price.toFloat()*cnt).toString()} €"
        }

        //val finalTotalBtn = binding.totalBtn
        totalButton.setOnClickListener {
            //addBasket(it, dish, cnt)
            val connect = Intent(this, ConnexionActivity::class.java)
            startActivity(connect)
        }
    }

    private fun setupViewPager(dish: DishModel) {
        binding.carrousel.adapter = PagerAdapter(this, dish.pictures)
        binding.nameDetails.text = dish.name_fr
        binding.descriptionDetails.text = dish.ingredients.joinToString(", ") { it.name_fr }
    }

    private fun addBasket(it: View, dish: DishModel, cnt: Int) {
        val filename = "myPocket.json"
        val quantity = cnt
        val file = File(cacheDir.absolutePath + filename)

        Snackbar.make(it, "add basket", Snackbar.LENGTH_LONG).show()
        var item: List<GetPocket> = ArrayList<GetPocket>()

        if (file.exists()) {
            item = Gson().fromJson(file.readText(), BasketActivity::class.java) as List<GetPocket>
        }

        file.writeText(Gson().toJson(item))

    }
}