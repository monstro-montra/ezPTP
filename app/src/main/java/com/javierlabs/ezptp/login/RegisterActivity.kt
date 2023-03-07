package com.javierlabs.ezptp.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.javierlabs.ezptp.MainActivity
import com.javierlabs.ezptp.R


class RegisterActivity : AppCompatActivity() {
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth
    private lateinit var signUpButton : Button
    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText


    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser //authenticate the current user
        if(currentUser != null){ //if currentUser is not null
            //switch to MainActivity
            val autoLogin = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(autoLogin)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = Firebase.auth

        //initialize all view elements
        signUpButton = findViewById(R.id.btnSignUp)
        editTextEmail = findViewById(R.id.edEmail)
        editTextPassword = findViewById(R.id.edPassword)

        //set onClickListener for the signUp button
        signUpButton.setOnClickListener {
            var email = editTextEmail.text.toString()
            var password = editTextEmail.text.toString()


            if(TextUtils.isEmpty(email)) { //if email string is empty, send a toast message.
                Toast.makeText(this@RegisterActivity, "Enter your Email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) { //if password string is empty, send a toast message.
                Toast.makeText(this@RegisterActivity, "Enter your Password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //if the user actually input content into the edit texts,
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    //if the task is successful
                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext, "Registration Successful.", Toast.LENGTH_SHORT).show() //toast message to show that the user's registration was successful
                        startActivity(Intent(this@RegisterActivity, MainActivity::class.java)) //go to MainActivity
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}