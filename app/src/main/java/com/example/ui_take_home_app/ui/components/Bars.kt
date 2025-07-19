package com.example.ui_take_home_app.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {

    val navItems = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            title = "Calculate",
            selectedIcon = Icons.Filled.Build, // todo find correct icon calculate
            unselectedIcon = Icons.Outlined.Build
        ),
        BottomNavItem(
            title = "Shipment",
            selectedIcon = Icons.Filled.Refresh, // todo find correct icon shipping
            unselectedIcon = Icons.Outlined.Refresh
        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // Calculate item width (accounting for padding and spacing)
    val itemWidth = (screenWidth) / navItems.size

    // Animated indicator position
    val indicatorOffset by animateDpAsState(
        targetValue = itemWidth * selectedIndex,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "indicator_offset"
    )



    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Box {
            // Animated top indicator
            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .size(itemWidth, height = 4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RectangleShape
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                navItems.forEachIndexed { index, item ->
                    BottomNavItem(
                        item = item,
                        isSelected = selectedIndex == index,
                        onClick = {
                            onItemSelected(index) }
                    )
                }
            }
        }
    }


}

@Composable
fun BottomNavItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val activeColor = MaterialTheme.colorScheme.primary
    val inActiveColor = MaterialTheme.colorScheme.inverseSurface
    val iconColor = if (isSelected) activeColor else inActiveColor
    val textColor = if (isSelected) activeColor else inActiveColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
            contentDescription = item.title,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item.title,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Navigation items data class
data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Preview
@Composable
fun BottomBarPreview(){
    UitakehomeappTheme {
        BottomBar()
    }
}