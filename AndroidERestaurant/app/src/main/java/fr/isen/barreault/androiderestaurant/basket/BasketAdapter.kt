package fr.isen.barreault.androiderestaurant.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.barreault.androiderestaurant.databinding.BasketViewDesignBinding
import fr.isen.barreault.androiderestaurant.model.GetPocket


class BasketAdapter(private val items: List<GetPocket>):
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(private val binding: BasketViewDesignBinding): RecyclerView.ViewHolder(binding.root){
        val dishName: TextView = binding.dishName
        val dishPicture: ImageView = binding.dishPicture
        val price: TextView = binding.dishPricePocket
        val quantity: TextView = binding.dishQuantityPocket
        val delete: ImageButton = binding.deleteBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(BasketViewDesignBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem = items[position]
        //holder.dishName.text = basketItem.dish.name
        //holder.price.text = "${basketItem.dish.prices.first().price}€"
        //holder.quantity.text = basketItem.quantity.toString()
        /*Picasso.get()
            .load(basketItem.)
            .placeholder(R.drawable.logo_chef)
            .into(holder.dishPicture)*/
    }
    override fun getItemCount(): Int {
        return items.count()
    }

}
