package fr.isen.barreault.androiderestaurant.connexion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.barreault.androiderestaurant.BaseActivity
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.ActivityConnexionBinding
import java.security.MessageDigest

class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnexionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* call default fragment : login */
        changeFragment(LoginFragment())

        val loginBtn = binding.loginBtn
        val registerBtn = binding.registerBtn

        loginBtn.setOnClickListener {
            changeFragment(LoginFragment())
        }
        registerBtn.setOnClickListener {
            changeFragment(RegisterFragment())
        }
    }

    /* change fragment between login and register */
    private fun changeFragment(fragment: Fragment){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit()
    }

}