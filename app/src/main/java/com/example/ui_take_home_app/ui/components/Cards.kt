package com.example.ui_take_home_app.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue

@Composable
fun AvailableVehicleItemCard(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.cargo_truck,
    title: String = "Ocean Frieght",
    body: String = "International",
) {
    var slideIconIn by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        slideIconIn = true
    }

    Card(
        modifier = modifier
            .width(150.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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

@Composable
fun HorizontalVehiclesList(
    modifier: Modifier = Modifier,
    vehicles: List<String> = listOf("1", "2", "3")
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(vehicles) { index, vehicle ->
            AvailableVehicleItemCard()

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