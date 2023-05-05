package com.javierlabs.ezptp.auth.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javierlabs.ezptp.auth.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel( private val repo: AuthRepository = AuthRepository()): ViewModel() {
    val currentUser = repo.currentUser

    val hasUser: Boolean
        get() = repo.hasUser()

    var loginUIState by mutableStateOf(LoginUIState())
        private set //can only be modified in the view model

    fun onEmailChanged(email: String) { //event that the user changes their username
        loginUIState = loginUIState.copy(email = email)
    }

    fun onPasswordChange(password: String) { //event that the user changes their password
        loginUIState = loginUIState.copy(password = password)
    }
    fun onRegEmailChange(regEmail: String) { //event that the user changes their registration username
        loginUIState = loginUIState.copy(regEmail = regEmail)
    }

    fun onRegPasswordChange(regPassword: String) { //event that the user changes their registration password
        loginUIState = loginUIState.copy(regPassword = regPassword)
    }

    fun onRegConfirmPasswordChange(regConfirmPassword: String) { //event that the user changes their registration confirm password
        loginUIState = loginUIState.copy(regConfirmPassword = regConfirmPassword)
    }

    private fun validateLogin() = //function for validating user login form (check to see if the following are not blank)
        loginUIState.email.isNotBlank() &&
                loginUIState.password.isNotBlank()

    private fun validateRegistration() = //function for validating user registration form (check to see if the following are not blank)
        loginUIState.regEmail.isNotBlank() &&
                loginUIState.regPassword.isNotBlank() &&
                loginUIState.regConfirmPassword.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch{
        try{
            if (!validateRegistration()){ //validate the registration (check if the form is not blank)
                throw IllegalArgumentException("Email and password can't be empty!") //if it is throw this exception
            }

            loginUIState = loginUIState.copy(isLoading = true) //set isLoading to true

            if (loginUIState.regPassword != loginUIState.regConfirmPassword){ //if regPassword and regConfirmPassword are not the same, throw exception
                throw IllegalArgumentException("Passwords do not match!")
            }

            loginUIState = loginUIState.copy(signUpError = null) // there was no error

            repo.createUser( //call the createUser function from AuthRepository.kt, passing in regUserName and regPassword
                loginUIState.regEmail,
                loginUIState.regPassword
            ) { isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(context, "Successful Login", Toast.LENGTH_SHORT).show()
                    loginUIState = loginUIState.copy(isSuccessful = true)
                } else {
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                    loginUIState = loginUIState.copy(isSuccessful = false)
                }
            }
        } catch (error: Exception){
            loginUIState = loginUIState.copy(signUpError = error.localizedMessage)
            error.printStackTrace() //send to logcat
        } finally {
            loginUIState = loginUIState.copy(isLoading = false) //set the loading bar back to false
        }
    }

    fun login(context: Context) = viewModelScope.launch{
        try{
            if (!validateLogin()){ //validate the registration (check if the form is not blank)
                throw IllegalArgumentException("Email and password can't be empty!") //if it is throw this exception
            }

            loginUIState = loginUIState.copy(isLoading = true) //set isLoading to true
            loginUIState = loginUIState.copy(loginError = null) //no login error

            repo.login( //call the createUser function from AuthRepository.kt, passing in regUserName and regPassword
                loginUIState.email,
                loginUIState.password
            ) { isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(context, "Successful Login", Toast.LENGTH_SHORT).show()
                    loginUIState = loginUIState.copy(isSuccessful = true)
                } else {
                    Toast.makeText(context, "Failed Login", Toast.LENGTH_SHORT).show()
                    loginUIState = loginUIState.copy(isSuccessful = false)
                }
            }
        } catch (error: Exception){
            loginUIState = loginUIState.copy(loginError = error.localizedMessage)
            error.printStackTrace() //send to logcat
        } finally {
            loginUIState = loginUIState.copy(isLoading = false) //set the loading bar back to false
        }
    }
}

data class LoginUIState(
    val email:String = "",
    val password:String = "",
    val regEmail:String = "",
    val regPassword:String = "",
    val regConfirmPassword:String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val signUpError:String? = null,
    val loginError:String? =null
)