package com.example.cryptoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
         //navController = navHostFragment.navController
        setContentView(R.layout.activity_main)

    }
    /*override fun onBackPressed() {
        super.onBackPressed()
        if (navController.currentDestination?.id == R.id.signInFragment){
            moveTaskToBack(true)
        }else{
            super.onBackPressed()
        }
    }*/

}

//3f:83:2c:7a:4c:dc:4b:92:14:c8:89:5b:37:d2:74:32:25:48:0a:b7