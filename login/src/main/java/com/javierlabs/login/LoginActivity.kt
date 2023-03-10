package com.javierlabs.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth


    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth.currentUser //authenticate the current user
        if (currentUser != null) { //if currentUser is not null
            //switch to MainActivity
            val autoLogin = Intent(this@LoginActivity, MenuActivity::class.java)
            startActivity(autoLogin)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) //set the content view to activity_login layout file

        mAuth = FirebaseAuth.getInstance()

    }
}