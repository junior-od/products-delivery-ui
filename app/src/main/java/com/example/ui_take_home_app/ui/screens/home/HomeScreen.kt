package com.example.ui_take_home_app.ui.screens.home

import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.components.DoubleTextWithIcon
import com.example.ui_take_home_app.ui.components.HorizontalVehiclesList
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.android.parcel.Parcelize

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    searchInProgress: Boolean,
    searchText: String
) {
    var firstTime by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        firstTime = true
    }

    Box(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)) {

        if (searchInProgress){
            SearchContent(searchText)
        }else {
            HomeContent(firstTime)
        }

    }
}

@Composable
fun HomeContent(
    contentAnimate: Boolean,
){
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState()
            )
            .fillMaxSize()
            .padding(bottom = 16.dp)

    ){
        AnimatedVisibility(visible = contentAnimate, enter = slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = {
                it
            }
        )) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){
                DoubleTextWithIcon(
                    hasBody = false,
                    hasIcon = false,
                    title = "Tracking",
                    titleStyle = MaterialTheme.typography.titleLarge.copy(
                        color = darkBlue,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){

                            DoubleTextWithIcon(
                                hasIcon = false,
                                title = "Shipment Number",
                                body = "NEJ20089934122231",
                                bodyStyle = MaterialTheme.typography.bodyLarge.copy(
                                    color = darkBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            )


                            AsyncImage(
                                model = R.drawable.mover_truck,
                                contentDescription = "shipment icon",
                                modifier = Modifier.size(32.dp),
                                contentScale = ContentScale.Crop
                            )

                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.inverseSurface, thickness = 0.1.dp)

                        Spacer(modifier = Modifier.height(12.dp))

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){

                            DoubleTextWithIcon(
                                modifier = Modifier.fillMaxWidth(0.6f),
                                icon = R.drawable.sender_package,
                                title = "Sender",
                                body = "Atlanta, 5243"
                            )


                            DoubleTextWithIcon(
                                modifier = Modifier.fillMaxWidth(),
                                hasIcon = false,
                                title = "Time",
                                body = "• 2 day -3 days"
                            )

                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){

                            DoubleTextWithIcon(
                                modifier = Modifier.fillMaxWidth(0.6f),
                                icon = R.drawable.receiver_package,
                                title = "Receiver",
                                body = "Chicago, 6342"
                            )


                            DoubleTextWithIcon(
                                modifier = Modifier.fillMaxWidth(),
                                hasIcon = false,
                                title = "Status",
                                body = "Waiting to collect"
                            )

                        }


                    }

                    HorizontalDivider(color = MaterialTheme.colorScheme.inverseSurface, thickness = 0.1.dp)

                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add Stop",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.secondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))



                }

                Spacer(modifier = Modifier.height(16.dp))

            }

        }
        AnimatedVisibility(visible = contentAnimate, enter = slideInVertically(
            animationSpec = tween(1000),
            initialOffsetY = {
                it
            }
        )) {
            DoubleTextWithIcon(
                modifier = Modifier.padding(horizontal = 16.dp),
                hasBody = false,
                hasIcon = false,
                title = "Available Vehicles",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    color = darkBlue,
                    fontWeight = FontWeight.SemiBold
                )
            )


        }
        if (contentAnimate){
            HorizontalVehiclesList()
        }
    }


}

@Composable
fun SearchContent(
    searchText: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        var searchedItems by rememberSaveable {
            mutableStateOf(
                listOf(
                    Search(id = 1, "Macbook pro M2", "#NE43857340857904 • Paris - Morocco"),
                    Search(id = 2,"Summer linen jacket", "#NEJ20089934122231 • Barcelona -> Paris"),
                    Search(id = 3,"Tapered-fit jeans AW", "#NEJ35870264978659 • Colombia - Paris"),
                    Search(id = 4,"Slim fit jeans AW", "#NEJ35870264978659 • Bogota - Dhaka"),
                    Search(id = 5,"Office setup desk", "#NEJ23481570754963 • France -> German")
                ).shuffled()
            )
        }
        LaunchedEffect(searchText) {
            if (searchText.isNotEmpty()){
                searchedItems = listOf(
                    Search(id = 6,"Macbook pro M2", "#NE43857340857904 • Paris - Morocco"),
                    Search(id = 7,"Summer linen jacket", "#NEJ20089934122231 • Barcelona -> Paris"),
                    Search(id = 8,"Tapered-fit jeans AW", "#NEJ35870264978659 • Colombia - Paris"),
                    Search(id = 9,"Office setup desk", "#NEJ23481570754963 • France -> German")
                ).shuffled()
            } else {
                searchedItems = listOf(
                    Search(id = 1, "Macbook pro M2", "#NE43857340857904 • Paris - Morocco"),
                    Search(id = 2,"Summer linen jacket", "#NEJ20089934122231 • Barcelona -> Paris"),
                    Search(id = 3,"Tapered-fit jeans AW", "#NEJ35870264978659 • Colombia - Paris"),
                    Search(id = 4,"Slim fit jeans AW", "#NEJ35870264978659 • Bogota - Dhaka"),
                    Search(id = 5,"Office setup desk", "#NEJ23481570754963 • France -> German")
                ).shuffled()
            }


        }



        Card(
            modifier = Modifier.animateContentSize(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(searchedItems, key = {_,item -> item.id}) { index, item ->
                    var firstTime by rememberSaveable {
                        mutableStateOf(false)
                    }

                    LaunchedEffect(Unit) {
                        firstTime = true
                    }


                    androidx.compose.animation.AnimatedVisibility(visible = firstTime, enter = slideInVertically(
                        animationSpec = tween(1000),
                        initialOffsetY = {
                            it
                        }
                    )) {
                        SearchItem(title = item.title, body = item.body)


                    }
                    if (index < searchedItems.lastIndex ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.inverseSurface,
                            thickness = 0.1.dp
                        )
                    }
                }
            }


        }

    }
}

@Composable
fun SearchItem(
    title: String,
    body: String
) {
    DoubleTextWithIcon(
        hasIcon = true,
        icon = R.drawable.search_package,
        title = title,
        body = body,
        titleStyle = MaterialTheme.typography.bodyLarge.copy(
            color = darkBlue,
            fontWeight = FontWeight.SemiBold
        ),
        bodyStyle = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.inverseSurface
        )
    )
}

@Parcelize
data class Search(
    val id: Int,
    val title: String,
    val body: String
): Parcelable