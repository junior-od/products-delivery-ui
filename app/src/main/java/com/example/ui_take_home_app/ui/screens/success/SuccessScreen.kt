package com.example.ui_take_home_app.ui.screens.success

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.components.AnimatedPriceCounter
import com.example.ui_take_home_app.ui.components.AppButton
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.coroutines.delay

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    goHomeClicked: () -> Unit = {}
) {

    BackHandler(enabled = false){
        // do nothing
    }





    var firstTime by rememberSaveable {
        mutableStateOf(false)
    }

    var buttonAnim by rememberSaveable {
        mutableStateOf(false)
    }

    // Animate size from 56dp to 80dp
    val animatedSize by animateDpAsState(
        targetValue = if (firstTime) 200.dp else 56.dp,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        ), label = "image anim"
    )

    LaunchedEffect(Unit) {
        firstTime = true
        delay(500)
        buttonAnim = true
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {


        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.animation.AnimatedVisibility(visible = firstTime,
                enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = {
                        it
                    }
                )) {
                AsyncImage(
                    model = R.drawable.logo,
                    contentDescription = "box icon",
                    modifier = Modifier
                        .width(250.dp)
                        .height(40.dp),
                    contentScale = ContentScale.Crop
                )
            }
            
            Spacer(modifier = Modifier.height(64.dp))


            AsyncImage(
                model = R.drawable.box_with_shadow,
                contentDescription = "box icon",
                modifier = Modifier.size(animatedSize),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(32.dp))

            androidx.compose.animation.AnimatedVisibility(visible = firstTime,
                enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = {
                        it
                    }
                )) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Estimated Amount",
                        style = MaterialTheme.typography.titleLarge.copy(darkBlue)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.Bottom) {
                        AnimatedPriceCounter(
                            targetValue = 1460
                        )

                        Text(
                            text = " USD",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = Color(0xFF5ABF92)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "This amount is estimated this will vary\nif you change your location or weight",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                }
            }

            androidx.compose.animation.AnimatedVisibility(visible = buttonAnim,
                enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = {
                        it
                    }
                )) {


                Column {
                    AppButton(
                        onClick = goHomeClicked,
                        text = "Back to home"
                    )
                    
                    Spacer(modifier = Modifier.height(4.dp))
                }

            }
        }


    }



}

@Composable
fun MoveMateHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "MoveMate",
            style = MaterialTheme.typography.displaySmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        TruckIcon()
    }
}

@Composable
fun TruckIcon() {
    AsyncImage(
        model = R.drawable.box_with_shadow,
        contentDescription = "box icon",
        modifier = Modifier.size(56.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun SuccessScreenPreview(){
    UitakehomeappTheme {
        SuccessScreen()
    }
}