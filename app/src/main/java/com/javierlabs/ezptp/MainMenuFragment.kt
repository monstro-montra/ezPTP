package com.javierlabs.ezptp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.javierlabs.ezptp.databinding.FragmentMainMenuBinding
import com.javierlabs.ezptp.login.LoginActivity


class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {
    private var fragmentMainMenuBinding : FragmentMainMenuBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var user : FirebaseUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainMenuBinding.bind(view)
        fragmentMainMenuBinding = binding

        mAuth = Firebase.auth
        user = mAuth.currentUser!!


        if (user == null){
            startActivity(Intent(context, LoginActivity::class.java))
        } else {
            val email = "Logged in as: " + user.email
            binding.userDetails.text = email
        }

        binding.startPTP.setOnClickListener { // upon clicking the start PTP button
            findNavController().navigate(MainMenuFragmentDirections.actionMainMenuFragmentToStartFragment()) //navigate to the StartFragment
        }
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}