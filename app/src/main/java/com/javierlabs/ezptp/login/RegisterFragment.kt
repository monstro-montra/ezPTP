package com.javierlabs.ezptp.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.javierlabs.ezptp.main_menu.MenuActivity
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var fragmentRegisterBinding : FragmentRegisterBinding? = null
    private lateinit var mAuth: FirebaseAuth



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegisterBinding.bind(view)

        mAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            val email: String = binding.edEmail.text.toString()
            val password: String = binding.edPassword.text.toString()


            if(TextUtils.isEmpty(email)) { //if email string is empty, send a toast message.
                Toast.makeText(activity, "Enter your Email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) { //if password string is empty, send a toast message.
                Toast.makeText(activity, "Enter your Password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            activity?.let { it ->
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Registration Successful.", Toast.LENGTH_SHORT).show()
                            activity?.let{
                                //if all is successful, go to MainActivity class
                                val goToMainActivity = Intent(it, MenuActivity::class.java)
                                it.startActivity(goToMainActivity)
                            }

                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(activity, "Authentication Failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        binding.alreadyHaveAccount.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRegisterBinding = null
    }
}