package com.example.ui_take_home_app.ui.screens.calculate

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.components.AppButton
import com.example.ui_take_home_app.ui.components.BoxSelectorCard
import com.example.ui_take_home_app.ui.components.Category
import com.example.ui_take_home_app.ui.components.CategorySelectionGrid
import com.example.ui_take_home_app.ui.components.DoubleTextWithIcon
import com.example.ui_take_home_app.ui.components.InputCard
import com.example.ui_take_home_app.ui.navigation.AppDestinations
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.coroutines.delay

@Composable
fun CalculateScreen(modifier: Modifier = Modifier, onCalculateClicked: () -> Unit = {}, onBackHome: () -> Unit = {}) {

    BackHandler(
        onBack = onBackHome
    )

    var firstTime by rememberSaveable {
        mutableStateOf(false)
    }

    var buttonAnim by rememberSaveable {
        mutableStateOf(false)
    }

    var senderText by rememberSaveable {
        mutableStateOf("")
    }

    var receiverText by rememberSaveable {
        mutableStateOf("")
    }

    var weightText by rememberSaveable {
        mutableStateOf("")
    }

    var categories by rememberSaveable {
        mutableStateOf(
            listOf(
                Category("documents", "Documents"),
                Category("glass", "Glass"),
                Category("liquid", "Liquid"),
                Category("food", "Food"),
                Category("electronic", "Electronic"),
                Category("product", "Product"),
                Category("others", "Others")
            )
        )
    }

    LaunchedEffect(Unit) {
        firstTime = true
        delay(500)
        buttonAnim = true
    }

     Column(modifier = modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState())
         .padding(16.dp)
         .background(color = MaterialTheme.colorScheme.background)) {

         AnimatedVisibility(
             visible = firstTime,
             enter = slideInVertically(
                 animationSpec = tween(1000),
                 initialOffsetY = {
                     it
                 }
             )
             ) {
             Column {
                 DoubleTextWithIcon(
                     hasBody = false,
                     hasIcon = false,
                     title = "Destination",
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
                         InputCard(
                             value = senderText,
                             onValueChange = {
                                senderText = it
                             },
                             placeholder = "Sender location",
                             icon = R.drawable.sender_location
                         )
                         Spacer(modifier = Modifier.height(8.dp))

                         InputCard(
                             value = receiverText,
                             onValueChange = {
                                receiverText = it
                             },
                             placeholder = "Receiver location",
                             icon = R.drawable.receiver_location
                         )

                         Spacer(modifier = Modifier.height(8.dp))

                         InputCard(
                             value = weightText,
                             onValueChange = {
                                weightText = it
                             },
                             placeholder = "Approx weight",
                             icon = R.drawable.weight
                         )
                     }


                 }


             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         AnimatedVisibility(
             visible = firstTime,
             enter = slideInVertically(
                 animationSpec = tween(1000),
                 initialOffsetY = {
                     it
                 }
             )
         ) {
             Column {

                 DoubleTextWithIcon(
                     hasIcon = false,
                     title = "Packaging",
                     body = "What are you sending?",
                     titleStyle = MaterialTheme.typography.titleLarge.copy(
                         color = darkBlue,
                         fontWeight = FontWeight.SemiBold
                     ),
                     bodyStyle = MaterialTheme.typography.titleMedium.copy(
                         color = MaterialTheme.colorScheme.inverseSurface,
                         fontWeight = FontWeight.SemiBold
                     )
                 )

                 Spacer(modifier = Modifier.height(16.dp))

                 BoxSelectorCard()

             }
         }

         Spacer(modifier = Modifier.height(16.dp))

         AnimatedVisibility(
             visible = firstTime,
             enter = slideInVertically(
                 animationSpec = tween(1000),
                 initialOffsetY = {
                     it
                 }
             )
         ) {
             Column {

                 DoubleTextWithIcon(
                     hasIcon = false,
                     title = "Categories",
                     body = "What are you sending?",
                     titleStyle = MaterialTheme.typography.titleLarge.copy(
                         color = darkBlue,
                         fontWeight = FontWeight.SemiBold
                     ),
                     bodyStyle = MaterialTheme.typography.titleMedium.copy(
                         color = MaterialTheme.colorScheme.inverseSurface,
                         fontWeight = FontWeight.SemiBold
                     )
                 )

             }
         }

         Spacer(modifier = Modifier.height(16.dp))


         if(firstTime){
             CategorySelectionGrid(
                 categories = categories,
                 onCategoryClick = { categoryId ->
                     categories = categories.map { category ->
                         if (category.id == categoryId) {
                             category.copy(isSelected = !category.isSelected)
                         } else {
                             category
                         }
                     }
                 }
             )

             Spacer(modifier = Modifier.height(64.dp))

             AnimatedVisibility(
                 visible = buttonAnim,
                 enter = slideInVertically(
                     animationSpec = tween(1000),
                     initialOffsetY = {
                         it
                     }
                 )
             ) {
                 AppButton(
                     onClick = onCalculateClicked
                 )
             }


             Spacer(modifier = Modifier.height(16.dp))

         }


    }


}