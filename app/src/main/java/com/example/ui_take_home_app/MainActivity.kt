package com.example.ui_take_home_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.ui_take_home_app.ui.components.BottomBar
import com.example.ui_take_home_app.ui.components.TopBarComponent
import com.example.ui_take_home_app.ui.navigation.AppNavHost
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

                LaunchedEffect(Unit) {
                    bottomBarVisibility = true
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBarComponent()
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = bottomBarVisibility,
                            enter = slideInVertically(
                                animationSpec = tween(1000),
                                initialOffsetY = {
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
                        navHostController = navHostController

                    )
                }
            }
        }
    }
}