package com.javierlabs.ezptp.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.javierlabs.ezptp.R
import com.javierlabs.ezptp.auth.Destination
import com.javierlabs.ezptp.themes.colorPrimaryFaded2
import com.javierlabs.ezptp.themes.colorWhite

//composable functions should not know anything about services or business logic. they should simply observe state changes emitted by the ViewModel. ViewModels actually access the service.
@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel? = null,
) {
    val loginUIState = loginViewModel?.loginUIState
    val isError = loginUIState?.loginError != null
    val context = LocalContext.current
    var revealPassword: MutableState<Boolean> = remember { mutableStateOf(false)}


    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text( //Login Text
            text = "Login",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(height = 140.dp)) //spacer between login text and the email textfield

        TextField( //textfield for email
            value = loginUIState?.email ?: "",
            onValueChange = { loginViewModel?.onPasswordChange(it) }, //update the login
            label = { Text ("Email") },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_email_24),
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(height = 25.dp))

        TextField( //textfield for password
            value = loginUIState?.password ?: "",
            onValueChange = { loginViewModel?.onPasswordChange(it)}, //update the password with function from the viewModel
            label = { Text ("Password") },
            visualTransformation = if (revealPassword.value) { //allows for the password to be hidden
                VisualTransformation.None
            } else {
               PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.baseline_lock_24),
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            ),
            trailingIcon = {
                if (revealPassword.value) {
                    IconButton(
                        onClick = {
                            revealPassword.value = false
                        },
                    ) {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                        }
                } else {
                    IconButton(
                        onClick = {
                            revealPassword.value = true
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(height = 30.dp)) //spacer between password and forgot password button

        Row(
            verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.width(width = 150.dp)) //spacer between password and log in button
            Text(
                text = "Forgot Password?",
                modifier = Modifier.clickable(
                    onClick = { navController.navigate(Destination.Register.route) }
                )
            )

        }
        
        Spacer(modifier = Modifier.height(height = 120.dp)) //spacer between forgot password and log in button

        Button( //login button
            onClick = { loginViewModel?.login(context) }, // call the login function from loginViewModel
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(colorPrimaryFaded2),
            modifier = Modifier
                .width(250.dp)
                .height(50.dp)
        ){
            Text(
                text = "Log In",
                color = colorWhite
            )
        }

        if(loginUIState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if(loginViewModel?.hasUser == true){
                navController.navigate(Destination.Home.route) //navigate to home screen
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginScreen(rememberNavController())
}