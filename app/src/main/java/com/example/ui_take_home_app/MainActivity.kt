package com.example.ui_take_home_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ui_take_home_app.ui.components.BottomBar
import com.example.ui_take_home_app.ui.components.TopBarComponent
import com.example.ui_take_home_app.ui.navigation.AppDestinations
import com.example.ui_take_home_app.ui.navigation.AppNavHost
import com.example.ui_take_home_app.ui.navigation.goToScreen
import com.example.ui_take_home_app.ui.navigation.popToScreenInBackStack
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UitakehomeappTheme {

                val navHostController = rememberNavController()

                var tabIndexSelected by rememberSaveable {
                    mutableIntStateOf(0)
                }

                var bottomBarVisibility by rememberSaveable {
                    mutableStateOf(false)
                }

                var searchText by rememberSaveable {
                    mutableStateOf("")
                }

                var searchInProgress by rememberSaveable {
                    mutableStateOf(false)
                }

                var topTabSelected by rememberSaveable {
                    mutableIntStateOf(0)
                }

                LaunchedEffect(tabIndexSelected) {
                    bottomBarVisibility = tabIndexSelected == 0 || tabIndexSelected == 3

                    when (tabIndexSelected) {
                        0 -> {
                            navHostController.popToScreenInBackStack(AppDestinations.Home)
                        }
                        1 -> {
                            navHostController.popToScreenInBackStack(AppDestinations.Calculate)
                        }

                        2 -> {
                            // shipment
                            navHostController.popToScreenInBackStack(AppDestinations.Shipment)

                        }

                        3 -> {
                            navHostController.popToScreenInBackStack(AppDestinations.Profile)
                        }
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBarComponent(
                            tabSelected = tabIndexSelected,
                            onSearchTextChange = {
                                searchText = it
                            },
                            onSearchOpen = {
                                searchInProgress = true
                            },
                            onTabSelected = {
                                topTabSelected = it
                            },
                            onSearchClosed = {
                                searchInProgress = false
                            },
                            onNavHome = {
                                tabIndexSelected = 0
                            }
                        )
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarVisibility && !searchInProgress,
                            enter = slideInVertically(
                                animationSpec = tween(1000),
                                initialOffsetY = {
                                    it
                                }
                            ),
                            exit = slideOutVertically(
                                animationSpec = tween(1000),
                                targetOffsetY = {
                                    it
                                }
                            )
                            ) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.tertiary
                            ) {
                                BottomBar(
                                    selectedIndex = tabIndexSelected,
                                    onItemSelected = {
                                        tabIndexSelected = it
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->

                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navHostController,
                        searchInProgress = searchInProgress,
                        searchText = searchText,
                        topTabSelected = topTabSelected,
                        onNavigateTo = {

                            tabIndexSelected = 5
                        },
                        onBackHome = {
                            tabIndexSelected = 0
                        }
                    )
                }
            }
        }
    }
}