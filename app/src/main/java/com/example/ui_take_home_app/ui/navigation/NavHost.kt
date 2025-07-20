package com.example.ui_take_home_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui_take_home_app.ui.screens.home.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: AppDestinations = AppDestinations.Home
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable<AppDestinations.Home> {
            HomeScreen(

            )
        }

    }

}

fun NavController.popToScreen(screen: AppDestinations) {
    return this.navigate(screen) {
        popUpTo(0) { inclusive = true }
    }

}

fun NavController.popToScreenInBackStack(screen: AppDestinations) {
    return this.navigate(screen) {
        popUpTo(screen) { inclusive = true }
    }

}