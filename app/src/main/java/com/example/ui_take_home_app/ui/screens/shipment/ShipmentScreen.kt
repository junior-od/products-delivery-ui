package com.example.ui_take_home_app.ui.screens.shipment

import android.os.Parcelable
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_take_home_app.ui.components.DeliveryNotificationCard
import com.example.ui_take_home_app.ui.components.DoubleTextWithIcon
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.android.parcel.Parcelize

@Composable
fun ShipmentScreen(
    modifier: Modifier = Modifier,
    topTabSelected: Int,
    onBackHome: () -> Unit = {}
) {

    BackHandler(
        onBack = onBackHome
    )

    var firstTime by rememberSaveable {
        mutableStateOf(false)
    }



    var history by remember {
        mutableStateOf(
             listOf(
        Shipment(2,"1"),
        Shipment(3,"2"),
        Shipment(4,"1"),
        Shipment(5,"0"),
        Shipment(6,"2"),
       )
        )
    }

    LaunchedEffect(topTabSelected) {

        when (topTabSelected) {
            2 -> {
                if(firstTime){
                    history = listOf(
                        Shipment(21,"0"),
                        Shipment(22,"0"),
                        Shipment(23,"0")
                    )
                }

            }

            else -> {
                history = listOf(
                    Shipment(25,"1"),
                    Shipment(36,"2"),
                    Shipment(46,"1"),
                    Shipment(56,"0"),
                    Shipment(66,"2"),
                )
            }
        }

    }
    LaunchedEffect(Unit) {
        firstTime = true
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {



        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            item {
                AnimatedVisibility(visible = firstTime, enter = slideInVertically(
                    animationSpec = tween(1000),
                    initialOffsetY = {
                        it
                    }
                )) {
                    DoubleTextWithIcon(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        hasBody = false,
                        hasIcon = false,
                        title = "Shipments",
                        titleStyle = MaterialTheme.typography.titleLarge.copy(
                            color = darkBlue,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

            }

            items(history, key = { it.id }) { index ->
                DeliveryNotificationCard(
                    status = index.status.toInt(),
                    delayTime = 0L
                )

            }


        }


        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)// Height of the blur area
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.9f),
                        )
                    )
                )
                .align(Alignment.BottomStart)
        )



    }



}

@Parcelize
data class Shipment(
    val id: Int,
    val status: String
): Parcelable

