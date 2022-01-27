package fr.isen.barreault.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DishAdapter(private val dList: List<ItemDishModel>, val clickDish: (ItemDishModel) -> Unit) :
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    /* Holds the views for adding it to image and text */
    class DishViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val dishPicture: ImageView = itemView.findViewById(R.id.dishPictureItem)
        val dishName: TextView = itemView.findViewById(R.id.dishNameItem)
        val dishPrice: TextView = itemView.findViewById(R.id.dishPriceItem)
    }

    /* create new views */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_view_design, parent, false)
        return DishViewHolder(view)
    }

    /* binds the list items to a view */
    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        /* sets image/text to the imageview from our itemHolder class */
        holder.dishName.text = dList[position].name
        holder.dishPicture.setImageResource(dList[position].picture)
        holder.dishPrice.text = dList[position].price
        /* */
        holder.itemView.setOnClickListener{
            clickDish(dList[position])
        }
    }

    /* return the number of the items in the list */
    override fun getItemCount(): Int {
        return dList.size
    }


}