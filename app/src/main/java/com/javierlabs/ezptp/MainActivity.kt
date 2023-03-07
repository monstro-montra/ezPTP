package com.javierlabs.ezptp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.javierlabs.ezptp.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var user : FirebaseUser
    private lateinit var logoutButton : Button
    private lateinit var userInfo : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = Firebase.auth
        user = mAuth.currentUser!!
        logoutButton = findViewById(R.id.logout)
        userInfo = findViewById(R.id.user_details)

        if (user == null){
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        } else {
            userInfo.text = user.email
        }

        logoutButton.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }
}