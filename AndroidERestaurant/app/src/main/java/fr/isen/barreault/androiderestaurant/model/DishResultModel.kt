package fr.isen.barreault.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DishResult(val data: List<CategoryModel>): Serializable

data class CategoryModel(val name_fr: String, val items: List<DishModel>): Serializable

data class DishModel(
    val name_fr: String,
    @SerializedName("images") val pictures: List<String>,
    val ingredients: List<IngredientModel>,
    val prices: List<PriceModel>,
): Serializable {
        fun getPicture() = if(pictures[0].isNotEmpty()) pictures[0] else null
        fun getFormattedPrice() = prices[0].price
    }

data class IngredientModel(val name_fr: String): Serializable

data class PriceModel(val price: String): Serializable