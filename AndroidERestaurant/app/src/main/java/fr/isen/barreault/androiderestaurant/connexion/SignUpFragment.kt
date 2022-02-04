package fr.isen.barreault.androiderestaurant.connexion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.databinding.FragmentSignupBinding
import fr.isen.barreault.androiderestaurant.dishes.FragmentPictDetails
import fr.isen.barreault.androiderestaurant.model.AccountModel
import org.json.JSONObject


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createBtn.setOnClickListener {

            if (validForm()) {
                val firstname = binding.firstnameEditInput.text.toString()
                val lastname = binding.lastnameEditInput.text.toString()
                val address = binding.addressEditInput.text.toString()
                val email = binding.emailEditInput.text.toString()
                val password = binding.passwordEditInput.text.toString()

                //request post
                val queue = Volley.newRequestQueue(context)
                val url = "http://test.api.catering.bluecodegames.com/user/register"
                val jsonObject = JSONObject()
                jsonObject.put("firstname", firstname)
                jsonObject.put("lastname", lastname)
                jsonObject.put("address", address)
                jsonObject.put("email", email)
                jsonObject.put("password", password)

                val request = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    { response ->
                        var gson = Gson()
                        var accountForm =
                            gson.fromJson(response.toString(), AccountModel::class.java)
                        Log.d("", "$response")
                    }, {
                        // Error in request
                        Log.e("", "Volley error: $it")
                    })
                queue.add(request)
                Snackbar.make(it, "succès enrollement", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun validForm(): Boolean {
        var error = true
        val errorEmptyField = "Champ vide"
        val errorPwdLength = "Saisir 8 caractères minimum"
        val errorInvalidEmail = "email invalide"

        /* if empty field */
        if (binding.firstnameEditInput.text.toString().trim().isEmpty()) {
            binding.firstnameEdit.error = errorEmptyField
            error = false
        } else {
            binding.firstnameEdit.error = null
        }
        if (binding.lastnameEditInput.text.toString().trim().isEmpty()) {
            binding.lastnameEdit.error = errorEmptyField
            error = false
        } else {
            binding.lastnameEdit.error = null
        }
        if (binding.addressEditInput.text.toString().trim().isEmpty()) {
            binding.addressEdit.error = errorEmptyField
            error = false
        } else {
            binding.addressEdit.error = null
        }
        if (binding.emailEditInput.text.toString().trim().isEmpty()) {
            binding.emailEdit.error = errorEmptyField
            error = false
        } else {
            binding.emailEdit.error = null
        }
        if (binding.passwordEditInput.text.toString().trim().isEmpty()) {
            binding.passwordEdit.error = errorEmptyField
            error = false
        } else {
            binding.passwordEdit.error = null
        }

        //if email form is invalid
        if (""".+\@.+\..+""".toRegex().matches(binding.emailEditInput.text.toString())) {

            // Clear error text
            binding.emailEdit.error = null
        } else {
            // Set error text
            binding.emailEdit.error = errorInvalidEmail
            error = false
        }

        //Not enough character password
        if (binding.passwordEditInput.text.toString().length < 8) {
            binding.passwordEdit.error = errorPwdLength
            error = false
        } else {
            binding.passwordEdit.error = null
        }
        return error
    }
}