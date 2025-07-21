package com.example.ui_take_home_app.ui.components

import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.delay

@Composable
fun AvailableVehicleItemCard(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.cargo_truck,
    title: String = "Ocean Frieght",
    body: String = "International",
) {

    var slideCardIn by rememberSaveable {
        mutableStateOf(false)
    }

    var slideIconIn by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        slideCardIn = true
        delay(500)
        slideIconIn = true
    }

    AnimatedVisibility(visible = slideCardIn, enter = slideInHorizontally(
        animationSpec = tween(1000),
        initialOffsetX = {
            it
        })) {
        Card(
            modifier = modifier
                .width(150.dp)
                .height(200.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (10).dp, y = (-10).dp)
                        .size(130.dp), visible = slideIconIn, enter = slideInHorizontally(
                        animationSpec = tween(1000),
                        initialOffsetX = {
                            it
                        }
                    )
                ) {
                    // Ship icon
                    AsyncImage(
                        model = icon,
                        contentDescription = "vehicle icon",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                // Header text
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {

                    androidx.compose.animation.AnimatedVisibility(
                        visible = slideIconIn, enter = slideInHorizontally(
                            animationSpec = tween(900),
                            initialOffsetX = {
                                it / 2
                            }
                        )
                    ) {
                        DoubleTextWithIcon(
                            hasIcon = false,
                            title = title,
                            body = body,
                            titleStyle = MaterialTheme.typography.titleMedium.copy(
                                color = darkBlue
                            ),
                            bodyStyle = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.inverseSurface
                            )
                        )
                    }


                }


            }


        }
    }


}

@Composable
fun HorizontalVehiclesList(
    modifier: Modifier = Modifier,
    vehicles: List<Vehicle> = listOf(Vehicle("Ocean Freight","International",R.drawable.cargo_ship), Vehicle("Cargo Freight","Reliable",R.drawable.cargo_truck), Vehicle("Air Freight","International",R.drawable.cargo_ship))
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(vehicles) { index, vehicle ->
            AvailableVehicleItemCard(
                icon = vehicle.icon,
                title = vehicle.title,
                body = vehicle.body
            )

        }
    }
}

@Parcelize
data class Vehicle(
    val title: String,
    val body: String,
    val icon: Int
): Parcelable

@Composable
fun DeliveryNotificationCard(
    modifier: Modifier = Modifier,
    trackingNumber: String = "#NEJ20089934122231",
    origin: String = "Atlanta",
    price: String = "$1400 USD",
    date: String = "Sep 20, 2023",
    status: Int = 0,
    delayTime: Long = 0L
) {

    val colorCode = when (status){
        0 -> {
            Color(0xFF10B981)
        }
        1 -> {
            Color(0xFFF59E0B)
        }
        else -> {
            Color(0xFF3B82F6)
        }
    }

    val statusName = when (status){
        0 -> {
            "in-progress"
        }
        1 -> {
            "Pending"
        }
        else -> {
            "loading"
        }
    }

    val icon = when (status){
        0 -> {
            R.drawable.ic_progress
        }
        1 -> {
            R.drawable.ic_pending
        }
        else -> {
            R.drawable.ic_loading
        }
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    var cardSlideUp by rememberSaveable {
        mutableStateOf(false)
    }

    var contentSlideUp by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(delayTime)
        cardSlideUp = true
        delay(100)
        contentSlideUp = true

    }


    AnimatedVisibility(visible = cardSlideUp,
        enter = fadeIn() + slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = {
                it
            }
        )

        ) {
        Card(
            modifier = modifier
                .width(screenWidth)
                .height(180.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {
            AnimatedVisibility(visible = contentSlideUp,enter = slideInVertically(
                animationSpec = tween(1000),
                initialOffsetY = {
                    it
                }
            )
            ) {
                // Main Content Row
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    // Left Content
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Status Row
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFF6F6F6),
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .padding(vertical = 4.dp, horizontal = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = "In Progress",
                                tint = colorCode,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = statusName,
                                color = colorCode,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        // Title
                        Text(
                            text = "Arriving today!",
                            style = MaterialTheme.typography.titleMedium.copy(color = darkBlue, fontWeight = FontWeight.Bold)
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Description
                        Text(
                            text = "Your delivery, $trackingNumber\nfrom $origin, is arriving today!" ,
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.inverseSurface, fontWeight = FontWeight.Medium),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Price and Date
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = price,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "•" ,
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.inverseSurface, fontWeight = FontWeight.Medium),
                            )

                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = date,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Medium
                                ),
                                color = darkBlue
                            )
                        }
                    }

                    // Package Icon
                    AsyncImage(
                        model = R.drawable.box_with_shadow,
                        contentDescription = "box icon",
                        modifier = Modifier.size(64.dp),
                        contentScale = ContentScale.Crop
                    )
                }

            }

        }

    }
}


@Preview(showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420"
)
@Composable
fun DeliveryNotificationCardPreview(){
    UitakehomeappTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            DeliveryNotificationCard()
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AvailableVehicleItemCardPreview() {
    UitakehomeappTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
//            AvailableVehicleItemCard()
            HorizontalVehiclesList()
        }

    }
}