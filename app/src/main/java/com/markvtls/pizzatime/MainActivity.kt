package com.markvtls.pizzatime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
/** This activity holds all the fragments */
class MainActivity : AppCompatActivity() {

    /** Navigation component controller and host fragment */
    private var navHostFragment: NavHostFragment? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment?.navController

        /** Initializing bottom navigation. */
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navController?.let { bottomNavBar.setupWithNavController(it) }

    }
}