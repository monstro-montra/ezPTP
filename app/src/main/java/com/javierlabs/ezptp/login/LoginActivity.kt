package com.javierlabs.ezptp.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.javierlabs.ezptp.MainActivity
import com.javierlabs.ezptp.R

class LoginActivity : AppCompatActivity() {
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginButton : Button
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var noAccount : TextView

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser //authenticate the current user
        if(currentUser != null){ //if currentUser is not null
            //switch to MainActivity
            val autoLogin = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(autoLogin)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) //set the content view to activity_login layout file

        //initialize mAuth
        mAuth = Firebase.auth

        //initialize all view elements
        loginButton = findViewById(R.id.loginBtn)
        editTextEmail = findViewById(R.id.et_email)
        editTextPassword = findViewById(R.id.et_password)
        noAccount  = findViewById(R.id.tvCreateAccount)

        //set action listener for the login button
        loginButton.setOnClickListener {
            //convert what's in editTextEmail and editTextPassword to email and password strings
            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()

            if(TextUtils.isEmpty(email)) { //if email string is empty, send a toast message.
                Toast.makeText(this@LoginActivity, "Enter your Email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) { //if password string is empty, send a toast message.
                Toast.makeText(this@LoginActivity, "Enter your Password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Login Successful.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java)) //go to MainActivity
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        //if the user doesn't have an account, they can choose to go to the RegisterActivity class
        noAccount.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}