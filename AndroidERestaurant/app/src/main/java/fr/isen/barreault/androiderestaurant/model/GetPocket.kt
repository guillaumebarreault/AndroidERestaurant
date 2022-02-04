package fr.isen.barreault.androiderestaurant.model

import java.io.Serializable


data class GetPocket(val dishModel: DishModel, val quantity: Int): Serializable

data class GetInPocket(val dish : String, val quantity: Int): Serializable