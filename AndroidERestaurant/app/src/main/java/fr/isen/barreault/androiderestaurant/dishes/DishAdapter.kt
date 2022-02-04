package fr.isen.barreault.androiderestaurant.dishes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.CardViewDesignBinding
import fr.isen.barreault.androiderestaurant.model.DishModel


class DishAdapter(private val dishList: List<DishModel>, val  clickDish: (DishModel) -> Unit) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    /* Holds the views for adding it to image and text */
    class DishViewHolder(private val binding: CardViewDesignBinding): RecyclerView.ViewHolder(binding.root) {
        val dishPicture: ImageView = binding.dishPicture
        val dishName: TextView = binding.dishName
        val dishPrice: TextView = binding.dishPrice
    }

    /* create new views */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CardViewDesignBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DishViewHolder(binding)
    }
    /* binds the list items to a view */
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        /* sets image/text to the imageview from our itemHolder class */
        holder.dishName.text = dishList[position].name_fr
        holder.dishPrice.text = dishList[position].getFormattedPrice()
        Picasso.get()
            .load(dishList[position].getPicture())
            .placeholder(R.drawable.logo_chef)
            .error(R.drawable.logo_chef)
            .into(holder.dishPicture)
        holder.itemView.setOnClickListener{
            clickDish(dishList[position])
        }
    }
    /* return the number of the items in the list */
    override fun getItemCount(): Int {
        return dishList.size
    }
}