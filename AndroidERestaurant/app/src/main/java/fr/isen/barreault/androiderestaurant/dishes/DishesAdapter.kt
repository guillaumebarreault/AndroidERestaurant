package fr.isen.barreault.androiderestaurant.dishes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.ViewDesignCardBinding
import fr.isen.barreault.androiderestaurant.model.DishModel

class DishesAdapter(private val dishList: List<DishModel>, val clickDish: (DishModel) -> Unit):
    RecyclerView.Adapter<DishesAdapter.DishViewHolder>() {

    class DishViewHolder(private val binding: ViewDesignCardBinding):
        RecyclerView.ViewHolder(binding.root) {
        val dishPicture: ImageView = binding.dishPicture
        val dishName: TextView = binding.dishName
        val dishPrice: TextView = binding.dishPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = ViewDesignCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val list = dishList[position]
        holder.dishName.text = list.name_fr
        holder.dishPrice.text = list.getFormattedPrice()
        Picasso.get()
            .load(list.getPicture())
            .placeholder(R.drawable.logo_chef)
            .error(R.drawable.logo_chef)
            .into(holder.dishPicture)
        holder.itemView.setOnClickListener{
            clickDish(list)
        }
    }

    override fun getItemCount(): Int {
        return dishList.size
    }
}