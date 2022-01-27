package fr.isen.barreault.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val item = intent.getStringExtra("item_list")
        findViewById<TextView>(R.id.detailsTitle).text = item
        //findViewById<ImageView>(R.id.detailsPicture).picture = item

    }
}