package fr.isen.barreault.androiderestaurant.connexion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.barreault.androiderestaurant.Constants
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.basket.BasketActivity
import fr.isen.barreault.androiderestaurant.databinding.FragmentLoginBinding
import fr.isen.barreault.androiderestaurant.model.RegisterModel
import org.json.JSONObject

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonValidate = binding.validateBtn

        buttonValidate.setOnClickListener(){
            login()
        }
    }

    private fun login() {
        val params = HashMap<String,String>()
        params["id_shop"] = "1"
        params["email"] = binding.emailConnectInput.text.toString()
        params["password"] = binding.passwordConnectInput.text.toString()

        var errorBool: Boolean
        errorBool = true

        if (TextUtils.isEmpty(binding.emailConnectInput.text)) {
            binding.emailConnect.error = getString(R.string.error)
            errorBool = false
        }
        else { binding.emailConnect.error = null }
        if (TextUtils.isEmpty(binding.passwordConnectInput.text)) {
            binding.passwordConnect.error = getString(R.string.error)
            errorBool = false
        }
        else { binding.passwordConnect.error = null }
        if (errorBool) {
            loadLogin(params)
        }
    }

    private fun loadLogin(params: HashMap<String, String>) {
        val queue = Volley.newRequestQueue(requireContext())
        val jsonObject = JSONObject(params as HashMap<*, *>)
        val jsonRequestLogin = JsonObjectRequest(
            Request.Method.POST, Constants.URL_LOGIN, jsonObject,
            { response ->
                val register =
                    GsonBuilder().create().fromJson(response.toString(), RegisterModel::class.java)
                val editor =
                    requireContext().getSharedPreferences(
                        Constants.APP_PREFS,
                        Context.MODE_PRIVATE
                    ).edit()
                editor.putString(Constants.USER_ID, register.data.uid)
                editor.apply()

                val intent = Intent(requireContext(), BasketActivity::class.java)
                startActivity(intent)

            }, {
                Log.d("Login", "error ${it}")
            })
        jsonRequestLogin.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f
        )
        queue.add(jsonRequestLogin)
    }

}
