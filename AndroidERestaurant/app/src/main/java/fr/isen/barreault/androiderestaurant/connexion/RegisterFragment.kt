package fr.isen.barreault.androiderestaurant.connexion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.databinding.FragmentRegisterBinding
import fr.isen.barreault.androiderestaurant.model.RegisterModel
import org.json.JSONObject
import java.security.MessageDigest


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createBtn.setOnClickListener { it ->
            if (validForm()) {
                val firstname = binding.firstnameEditInput.text.toString()
                val lastname = binding.lastnameEditInput.text.toString()
                val address = binding.addressEditInput.text.toString()
                val email = binding.emailEditInput.text.toString()
                val password = binding.passwordEditInput.text.toString()
                val hashpwd = hash(password)
                loadRegister(firstname, lastname, address, email, hashpwd, it)
            }
        }
    }

    private fun loadRegister(
        firstname: String,
        lastname: String,
        address: String,
        email: String,
        hash: String,
        //password: String,
        it: View
    ) {
        val queue = Volley.newRequestQueue(context)
        val jsonObject = JSONObject()

        jsonObject.put(Constants.ID_SHOP, Constants.KEY_SHOP)
        jsonObject.put("firstname", firstname)
        jsonObject.put("lastname", lastname)
        jsonObject.put("address", address)
        jsonObject.put("email", email)

        // jsonObject.put("password", password)
        jsonObject.put("password", hash)

        val request = JsonObjectRequest(
            Request.Method.POST, Constants.URL_REGISTER, jsonObject,
            { response ->
                val gson = Gson()
                var accountForm = gson.fromJson(response.toString(), RegisterModel::class.java)
                //Log.i("INFO", "$response")
            }, {
                // Error in request
                //Log.e("ERROR", "Volley error: $it")
            })
        queue.add(request)
        Snackbar.make(it, "succès enrollement", Snackbar.LENGTH_LONG).show()
    }

    private fun validForm(): Boolean {
        var error: Boolean = true

        val firstnameEditInput = binding.firstnameEditInput
        val firstnameEdit = binding.firstnameEdit
        val lastnameEditInput = binding.lastnameEditInput
        val lastnameEdit =  binding.lastnameEdit
        val addressEditInput = binding.addressEditInput
        val addressEdit =  binding.addressEdit
        val emailEditInput = binding.emailEditInput
        val emailEdit = binding.emailEdit
        val passwordEditInput = binding.passwordEditInput
        val passwordEdit = binding.passwordEdit

        /* if empty field */
        error = checkEmptyField(firstnameEditInput, firstnameEdit, error)
        error = checkEmptyField(lastnameEditInput, lastnameEdit, error)
        error = checkEmptyField(addressEditInput, addressEdit, error)
        error = checkEmptyField(emailEditInput, emailEdit, error)
        error = checkEmptyField(passwordEditInput, passwordEdit, error)
        //if email form is invalid
        error = checkEmailForm(emailEditInput, emailEdit, error)
        //Not enough character password
        error = lengthPasswd(passwordEditInput, passwordEdit, error)

        return error
    }

    private fun checkEmptyField(EditInput: TextInputEditText, Edit: TextInputLayout, error: Boolean): Boolean {
        var error1 = error
        if (EditInput.text.toString().trim().isEmpty()) {
            Edit.error = Constants.EMPTY_FIELD
            error1 = false
        } else {
            Edit.error = null
        }
        return error1
    }

    private fun checkEmailForm(emailInput: TextInputEditText, email: TextInputLayout, error: Boolean): Boolean {
        var error1 = error
        if (""".+\@.+\..+""".toRegex().matches(emailInput.text.toString())) {
            // Clear error text
            email.error = null
        } else {
            // Set error text
            email.error = Constants.INVALID_EMAIL
            error1 = false
        }
        return error1
    }

    private fun lengthPasswd(passwdInput: TextInputEditText, passwdEdit: TextInputLayout, error: Boolean): Boolean {
        var error1 = error
        if (passwdInput.text.toString().length < 8) {
            passwdEdit.error = Constants.INVALID_PASSWD_LENGHT
            error1 = false
        } else {
            passwdEdit.error = null
        }
        return error1
    }

    private fun hash(string: String): String {
        val bytes = this.toString().toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}