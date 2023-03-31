package com.javierlabs.ezptp.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.javierlabs.ezptp.main.ptp.MainActivity
import com.javierlabs.ezptp.R

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
        setContentView(R.layout.auth_activity_login) //set the content view to activity_login layout file

        mAuth = FirebaseAuth.getInstance()

    }
}