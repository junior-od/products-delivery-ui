package com.example.ui_take_home_app.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme

@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String = "Calculate",
    animationDurationMs: Int = 200,
    shrinkScale: Dp = 16.dp
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedPadding by animateDpAsState(
        targetValue = if (isPressed) shrinkScale else 0.dp,
        animationSpec = tween(
            durationMillis = animationDurationMs,
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            if (isPressed) {
                isPressed = false
            }
        },
        label = "button_width_animation"
    )


    Button(
        onClick = {
            if (enabled && !isPressed) {
                isPressed = true
                onClick()
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = animatedPadding),
        enabled = enabled,
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary, // Orange color
            contentColor = MaterialTheme.colorScheme.tertiary
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 1.dp,
            pressedElevation = 1.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AppButtonPreview(){
    UitakehomeappTheme {
        AppButton(onClick = {  })
    }
}
