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
import com.javierlabs.ezptp.databinding.FragmentLoginBinding


class LoginFragment : Fragment(R.layout.fragment_login) {
    private var fragmentLoginBinding : FragmentLoginBinding? = null
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        mAuth = FirebaseAuth.getInstance()

        //initialize all view elements
        binding.loginBtn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(TextUtils.isEmpty(email)) { //if email string is empty, send a toast message.
                Toast.makeText(activity, "Enter your Email.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) { //if password string is empty, send a toast message.
                Toast.makeText(activity, "Enter your Password.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            activity?.let { it ->
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity, "Login Successful.", Toast.LENGTH_SHORT).show()
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

        //if the user doesn't have an account, they can choose to go to the RegisterActivity class
        binding.tvCreateAccount.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLoginBinding = null
    }


}

