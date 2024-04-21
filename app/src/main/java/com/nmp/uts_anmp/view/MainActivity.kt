package com.nmp.uts_anmp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.nmp.uts_anmp.R
import com.nmp.uts_anmp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> hideBottomNavigationBar()
                else -> showBottomNavigationBar()
            }
        }


        bind.bottomNav.setupWithNavController(navController)

//        finish()
//        startActivity(Intent(this, AuthActivity::class.java))
    }

    private fun hideBottomNavigationBar() {
        bind.bottomNav.visibility = View.GONE
    }

    private fun showBottomNavigationBar() {
        bind.bottomNav.visibility = View.VISIBLE
    }
}