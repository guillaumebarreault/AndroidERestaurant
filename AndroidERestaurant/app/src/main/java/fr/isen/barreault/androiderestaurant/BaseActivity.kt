package fr.isen.barreault.androiderestaurant

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.scottyab.rootbeer.RootBeer
import fr.isen.barreault.androiderestaurant.basket.BasketActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val basketView = menu.findItem(R.id.icBasket).actionView
        val inBasketCounter = (basketView.findViewById(R.id.inBasketCnt) as? TextView)
        val count = getSharedPreferences(
            Constants.APP_PREFS,
            Context.MODE_PRIVATE
        ).getInt(Constants.CNT_BASKET, 0)

        if (inBasketCounter != null) {
            inBasketCounter.text = count.toString()
            inBasketCounter.isVisible = count > 0
        }

        if (count > 0) {
            basketView.setOnClickListener {
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onResume() {
       /*if (RootBeer(this).isRooted) {
            finish()
        }*/
        super.onResume()
        invalidateOptionsMenu()

    }
}