package com.example.ui_take_home_app.ui.components

import android.os.Parcelable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue
import kotlinx.parcelize.Parcelize

@Composable
fun InputCard(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Sender location",
    icon: Int = R.drawable.ic_profile_sample
    ) {
    Row(
        modifier = modifier
            .background(color = Color(0xFFF8F8F8), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon inside bordered box
        AsyncImage(
            model = icon,
            contentDescription = "box icon",
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Vertical divider
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(MaterialTheme.colorScheme.inverseSurface)
        )

        // Input field (styled like plain text)
        TextField(
            modifier = Modifier.padding(vertical = 8.dp),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = darkBlue,
                fontWeight = FontWeight.Medium
            ),
            placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    )
            },
            colors = TextFieldDefaults.colors().copy(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, focusedTextColor = darkBlue, unfocusedTextColor = darkBlue, focusedPlaceholderColor = MaterialTheme.colorScheme.inverseSurface, unfocusedPlaceholderColor = MaterialTheme.colorScheme.inverseSurface, unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent)
        )
    }
}

@Composable
fun BoxSelectorCard(
    modifier: Modifier = Modifier,
    title: String = "Box",
    icon: Int = R.drawable.box
    // Replace with your icon
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.tertiary)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon inside bordered box
            AsyncImage(
                model = icon,
                contentDescription = "box icon",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Divider
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(24.dp)
                    .background(MaterialTheme.colorScheme.inverseSurface)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Text
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = darkBlue
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp)
            )

            // Chevron down icon
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Dropdown",
                tint = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}
@Parcelize
data class Category(
    val id: String,
    val name: String,
    val isSelected: Boolean = false
): Parcelable


@Composable
fun CategoryButton(
    category: Category,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (category.isSelected) {
        darkBlue // Dark blue-gray
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    val textColor = if (category.isSelected) {
        MaterialTheme.colorScheme.tertiary
    } else {
        darkBlue
    }

    val borderColor = if (category.isSelected) {
        Color.Transparent
    } else {
       darkBlue
    }

    var slideInLeft by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        slideInLeft = true
    }

    AnimatedVisibility(
        visible = slideInLeft,
        enter = slideInHorizontally(
            animationSpec = tween(1000),
            initialOffsetX = { it }
        )
        ) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { onCategoryClick(category.id) }
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .animateContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (category.isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Text(
                    text = category.name,
                    color = textColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (category.isSelected) FontWeight.Medium else FontWeight.Normal
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySelectionGrid(
    categories: List<Category>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { category ->
            CategoryButton(
                category = category,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySelectionGridPreview() {
    UitakehomeappTheme {
        var categories by rememberSaveable {
            mutableStateOf(
                listOf(
                    Category("documents", "Documents"),
                    Category("glass", "Glass"),
                    Category("liquid", "Liquid"),
                    Category("food", "Food"),
                    Category("electronic", "Electronic", isSelected = true),
                    Category("product", "Product"),
                    Category("others", "Others")
                )
            )
        }

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

    }
}



@Preview
@Composable
fun InputFieldCardPreview(){
    UitakehomeappTheme {
        InputCard(
            value = "kjkjk",
            onValueChange = {

            }
        )
    }
}

@Preview
@Composable
fun BoxSelectorCardPreview(){
    UitakehomeappTheme {
        BoxSelectorCard()
    }
}