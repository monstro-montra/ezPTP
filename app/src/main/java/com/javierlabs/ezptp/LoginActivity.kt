package com.javierlabs.ezptp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginButton : Button
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : Button
    private lateinit var noAccount : TextView

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser //authenticate the current user
        if(currentUser != null){ //if currentUser is not null
            //switch to MainActivity
            val autoLogin = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(autoLogin)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //initialize mAuth
        mAuth = Firebase.auth

        //initialize all view elements
        loginButton = findViewById(R.id.loginBtn)
        editTextEmail = findViewById(R.id.et_email)
        editTextPassword = findViewById(R.id.et_password)
        noAccount  = findViewById(R.id.tvCreateAccount)


    }
}