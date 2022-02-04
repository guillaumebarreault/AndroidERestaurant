package fr.isen.barreault.androiderestaurant.connexion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import fr.isen.barreault.androiderestaurant.HomeActivity
import fr.isen.barreault.androiderestaurant.R
import fr.isen.barreault.androiderestaurant.databinding.ActivityConnexionBinding

class ConnexionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnexionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeBtn = binding.backMenu
        homeBtn.setOnClickListener {
            val home = Intent(this, HomeActivity::class.java)
            startActivity(home)
        }

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, SignUpFragment()).commit()


        val loginBtn = binding.loginBtn
        val signupBtn = binding.signupBtn

        loginBtn.setOnClickListener {
            changeToLogin()
        }
        signupBtn.setOnClickListener {
            changeToSignUp()
        }


    }
    private fun changeToLogin() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, LoginFragment()).commit()
    }
    private fun changeToSignUp() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, SignUpFragment()).commit()

    }
}