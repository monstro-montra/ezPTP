package com.javierlabs.ezptp.auth.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Text("Hello world!")
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}