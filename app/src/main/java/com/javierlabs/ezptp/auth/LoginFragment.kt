package com.javierlabs.ezptp.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Button
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.auth.login.LoginViewModel
import com.javierlabs.ezptp.databinding.AuthFragmentLoginBinding

//import com.javierlabs.ezptp.databinding.AuthFragmentLoginBinding


class LoginFragment : Fragment(R.layout.auth_fragment_login) {
    private var fragmentLoginBinding : AuthFragmentLoginBinding? = null
    //lateinit guarantees that the variable will be initialized later
    private lateinit var mAuth: FirebaseAuth
    private lateinit var composeView: ComposeView
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = AuthFragmentLoginBinding.bind(view)
        fragmentLoginBinding = binding

        binding.loginBtn.setContent{
            Button(onClick = {

            }){

            }
        }

//
//        mAuth = FirebaseAuth.getInstance()
//
//        //initialize all view elements
//        binding.loginBtn.setOnClickListener {
//            val email = binding.etEmail.text.toString()
//            val password = binding.etPassword.text.toString()
//
//            if(TextUtils.isEmpty(email)) { //if email string is empty, send a toast message.
//                Toast.makeText(activity, "Enter your Email.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(TextUtils.isEmpty(password)) { //if password string is empty, send a toast message.
//                Toast.makeText(activity, "Enter your Password.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            activity?.let { it ->
//                mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(it) { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(activity, "Login Successful.", Toast.LENGTH_SHORT).show()
//                            activity?.let{
//                                //if all is successful, go to MainActivity class
//                                val goToMainActivity = Intent(it, MainActivity::class.java)
//                                it.startActivity(goToMainActivity)
//                            }
//
//                        }
//                    }
//                    .addOnFailureListener{
//                        Toast.makeText(activity, "Authentication Failed ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
//                    }
//            }
//        }
//
//        //if the user doesn't have an account, they can choose to go to the RegisterActivity class
//        binding.tvCreateAccount.setOnClickListener{
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
//        }


    }


}

