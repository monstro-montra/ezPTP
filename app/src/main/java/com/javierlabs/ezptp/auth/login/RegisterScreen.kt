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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
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

@Composable
fun RegisterScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel? = null,
) {
    val loginUIState = loginViewModel?.loginUIState
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
            text = "Register",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(height = 140.dp)) //spacer between login text and the email text field

        TextField( //text field for email
            value = loginUIState?.regEmail ?: "",
            onValueChange = { loginViewModel?.onRegEmailChange(it)}, //call onRegEmailChange function from viewmodel
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

        TextField( //text field for password
            value = loginUIState?.regPassword ?: "",
            onValueChange = { loginViewModel?.onRegPasswordChange(it)}, //call the onRegPasswordChange function from the viewmodel
            label = { Text ("Password") },
            visualTransformation = if (revealPassword.value) {
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

        Spacer(modifier = Modifier.height(height = 25.dp))

        TextField( //text field for re-enter password
            value = loginUIState?.regConfirmPassword ?: "",
            onValueChange = { loginViewModel?.onRegPasswordChange(it)}, //call the onRegPasswordChange function from viewmodel
            label = { Text ("Re-Enter Password") },
            visualTransformation = if (revealPassword.value) {
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

        Spacer(modifier = Modifier.height(height = 30.dp)) //spacer between password and already have an account password button

        Row(
            verticalAlignment = Alignment.CenterVertically){
            Spacer(modifier = Modifier.width(width = 150.dp)) //spacer between password and log in button
            Text(
                text = "Already have an account?",
                modifier = Modifier.clickable(
                    onClick = { navController.navigate(Destination.Login.route) }
                )
            )

        }

        Spacer(modifier = Modifier.height(height = 120.dp)) //spacer between forgot password and log in button

        Button( //login button
            onClick = { loginViewModel?.createUser(context) },
            shape = RoundedCornerShape(25),
            colors = ButtonDefaults.buttonColors(colorPrimaryFaded2),
            modifier = Modifier.width(250.dp)
                .height(50.dp)
        ){
            Text(
                text = "Register",
                color = colorWhite
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterScreen(rememberNavController())
}
