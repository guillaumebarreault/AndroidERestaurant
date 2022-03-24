package fr.isen.barreault.androiderestaurant.model

import com.google.gson.annotations.SerializedName

data class LoginModel(@SerializedName("id") val uid: String)