package com.example.datingjetpack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.datingjetpack.screens.onboarding.OnboardingScreen

@Composable
fun DatingNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DatingScreens.OnboardingScreen.name) {
        composable(DatingScreens.OnboardingScreen.name) {
            OnboardingScreen(navController = navController)
        }
    }
}