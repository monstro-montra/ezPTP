package com.javierlabs.ezptp.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.javierlabs.ezptp.main.ptp.MainActivity

sealed class Destination (val route: String){
    object Login: Destination("login")
    object Register: Destination("register")
}

class AuthActivity : AppCompatActivity() {
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth.currentUser //authenticate the current user
        if (currentUser != null) { //if currentUser is not null
            //switch to MainActivity
            val autoLogin = Intent(this@AuthActivity, MainActivity::class.java)
            startActivity(autoLogin)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
                NavigationAppHost(navController = navController)
            }
        }
    }
}

@Composable
fun NavigationAppHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "login") {
        //all of the possible destinations for the nav controller
        composable(Destination.Login.route) { LoginScreen(navController) }
        composable(Destination.Register.route){ RegisterScreen(navController) }
    }
}