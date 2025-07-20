package com.example.ui_take_home_app.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestinationsInfo {
    val title: String?
}


@Serializable
sealed class AppDestinations: AppDestinationsInfo {

    /** home screen pointer */
    @Serializable
    data object Home: AppDestinations() {
        override val title: String
            get() = "Home"
    }

    /** calculate screen pointer */
    @Serializable
    data object Calculate: AppDestinations() {
        override val title: String
            get() = "Calculate"
    }

    /** shipment screen pointer */
    @Serializable
    data object Shipment: AppDestinations() {
        override val title: String
            get() = "Shipment History"
    }

    /** success screen pointer */
    @Serializable
    data object Success: AppDestinations() {
        override val title: String
            get() = "Success"
    }
}

