package com.example.ui_take_home_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui_take_home_app.ui.screens.calculate.CalculateScreen
import com.example.ui_take_home_app.ui.screens.home.HomeScreen
import com.example.ui_take_home_app.ui.screens.shipment.ShipmentScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: AppDestinations = AppDestinations.Home,
    searchInProgress: Boolean = false,
    searchText: String = "",
    topTabSelected : Int
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {

        composable<AppDestinations.Home> {
            HomeScreen(
               searchInProgress = searchInProgress,
               searchText =  searchText
            )
        }

        composable<AppDestinations.Calculate>{
            CalculateScreen()
        }

        composable<AppDestinations.Shipment> {
            ShipmentScreen(
                topTabSelected = topTabSelected
            )
        }

    }

}

fun NavController.popToScreen(screen: AppDestinations) {
    return this.navigate(screen) {
        popUpTo(0) { inclusive = true }
    }

}

fun NavController.goToScreen(screen: AppDestinations) {
    return this.navigate(screen){
        launchSingleTop = true

        restoreState = true
        popBackStack(
            screen,
            inclusive = false,
            saveState = true
        )
    }
}

fun NavController.popToScreenInBackStack(screen: AppDestinations) {
    return this.navigate(screen) {
        popUpTo(screen) { inclusive = true }
    }

}