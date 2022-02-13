package fr.isen.barreault.androiderestaurant.model

import java.io.Serializable

data class GetPocket(val dish: DishModel, var quantity: Int): Serializable

data class InBasket(val items: MutableList<GetPocket>, var quantity: Int): Serializable