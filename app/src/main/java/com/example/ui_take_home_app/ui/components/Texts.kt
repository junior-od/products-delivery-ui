package com.example.ui_take_home_app.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue

@Composable
fun DoubleTextWithIcon(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_profile,
    title: String = "Sender",
    body: String = "Atlanta, 5243",
    hasTitle: Boolean = true,
    hasBody: Boolean = true,
    hasActiveIcon: Boolean = false,
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color =  MaterialTheme.colorScheme.inverseSurface
    ),
    bodyStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        color = darkBlue
    ),
    hasIcon: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (hasIcon) {
           // Package icon
            AsyncImage(
                model = icon,
                contentDescription = "sender icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))
        }


        // Text information
        Column {
            if (hasTitle) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    style = titleStyle
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(hasActiveIcon){
                    AsyncImage(
                        model = R.drawable.green_circle_icon,
                        contentDescription = "box icon",
                        modifier = Modifier.size(8.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                }

                if (hasBody){
                    Text(
                        text = body,
                        textAlign = TextAlign.Left,
                        style = bodyStyle
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedPriceCounter(
    targetValue: Int = 1460,
    durationMillis: Int = 1000
) {
    val animatedValue = remember { Animatable(0f) }

    LaunchedEffect(targetValue) {
        animatedValue.animateTo(
            targetValue.toFloat(),
            animationSpec = tween(durationMillis = durationMillis, easing = LinearOutSlowInEasing)
        )
    }

    val displayValue = animatedValue.value.toInt()

    Text(
        text = "$$displayValue",
        style = MaterialTheme.typography.titleLarge.copy(
            color = Color(0xFF5ABF92)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SenderInfoPreview() {
    UitakehomeappTheme {
        Column {
            DoubleTextWithIcon()

            DoubleTextWithIcon(
                hasIcon = false
            )

            DoubleTextWithIcon(
                hasIcon = false,
                bodyStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = darkBlue,
                    fontWeight = FontWeight.Bold
                )
            )

            DoubleTextWithIcon(
                hasBody = false,
                hasIcon = false,
                title = "Tracking",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    color = darkBlue,
                    fontWeight = FontWeight.SemiBold
                )
            )

            DoubleTextWithIcon(
                hasIcon = false,
                title = "Ocean Freight",
                body = "International",
                titleStyle = MaterialTheme.typography.titleLarge.copy(
                    color = darkBlue
                ),
                bodyStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            )

            AnimatedPriceCounter()
        }




    }
}