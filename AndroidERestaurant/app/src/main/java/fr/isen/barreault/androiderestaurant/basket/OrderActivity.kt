package fr.isen.barreault.androiderestaurant.basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.isVisible
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.CipherSharedPrefs
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.ActivityOrderBinding
import fr.isen.barreault.androiderestaurant.model.InBasket
import org.json.JSONObject
import java.io.File

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = File(cacheDir.absolutePath + Constants.NAME_FILE)

        if (file.exists()) {
            if (file.readText().isNotEmpty()) {
                loadOrder(file)
            } 
            else { displayDelivery(true) }
        } 
        else { displayDelivery(true) }
    }

    private fun loadOrder(file: File) {
        val queue = Volley.newRequestQueue(this)
        val jsonObject = JSONObject()
        val recup = file.readText()
        val result = Gson().fromJson(recup, InBasket::class.java)
        val id_user = CipherSharedPrefs.getInstance(this).getEncryptedPrefs("id_user")

        jsonObject.put("msg", recup)
        jsonObject.put(Constants.ID_SHOP, Constants.KEY_SHOP)
        jsonObject.put("id_user", id_user)

        val jsonRequestOrder = JsonObjectRequest(
            Request.Method.POST, Constants.URL_ORDER, jsonObject,
            {
                if (result.quantity == 0) displayDelivery(true)
                else displayDelivery(false)
            }, {
                displayDelivery(true)
            })
        jsonRequestOrder.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f
        )
        queue.add(jsonRequestOrder)
    }

    private fun displayDelivery(error: Boolean) {
        if (error) {
            binding.deliveryText.isVisible = false
            binding.errorText.isVisible = true
        }
        else {
            showGif()
            binding.icWarning.isVisible = false
            binding.deliveryText.isVisible = true
            binding.errorText.isVisible = false
        }
    }

    private fun showGif() {
        val imageView: ImageView = binding.deliveryPict
        Glide.with(this).load(R.drawable.ic_delivery).into(imageView)
    }

}