package fr.isen.barreault.androiderestaurant.basket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.ViewDesignBasketBinding
import fr.isen.barreault.androiderestaurant.model.GetPocket

@SuppressLint("SetTextI18n")

class BasketAdapter(private val items: MutableList<GetPocket>, private val onClickAdd: (GetPocket) -> Unit):
    RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(private val binding: ViewDesignBasketBinding):
        RecyclerView.ViewHolder(binding.root) {

        val dishName: TextView = binding.dishName
        val dishPicture: ImageView = binding.dishPicture
        val quantity: TextView = binding.dishQuantityPocket
        val delete: ImageButton = binding.deleteBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ViewDesignBasketBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val itemSelected = items[position]

        holder.dishName.text = itemSelected.dish.name_fr
        holder.quantity.text = "Quantité: ${itemSelected.quantity}"
        Picasso.get()
            .load(itemSelected.dish.getPicture())
            .placeholder(R.drawable.logo_chef)
            .into(holder.dishPicture)
        holder.delete.setOnClickListener {
            if(position < items.size) {
                onClickAdd.invoke(itemSelected)
                items.remove(itemSelected)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
