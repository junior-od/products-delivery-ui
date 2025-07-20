package com.example.ui_take_home_app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ui_take_home_app.R
import com.example.ui_take_home_app.ui.theme.UitakehomeappTheme
import com.example.ui_take_home_app.ui.theme.darkBlue
import com.example.ui_take_home_app.ui.theme.darkGray
import com.example.ui_take_home_app.ui.theme.lightPurple
import com.example.ui_take_home_app.ui.theme.secondary

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {

    val navItems = listOf(
        BottomNavItem(
            title = "Home",
            selectedIcon = R.drawable.ic_home,
            unselectedIcon = R.drawable.ic_home
        ),
        BottomNavItem(
            title = "Calculate",
            selectedIcon = R.drawable.ic_math,
            unselectedIcon = R.drawable.ic_math
        ),
        BottomNavItem(
            title = "Shipment",
            selectedIcon = R.drawable.ic_shipment,
            unselectedIcon = R.drawable.ic_shipment
        ),
        BottomNavItem(
            title = "Profile",
            selectedIcon = R.drawable.ic_profile,
            unselectedIcon = R.drawable.ic_profile
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
            dampingRatio = Spring.DampingRatioLowBouncy,
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
                            onItemSelected(index)
                        }
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
            painter = painterResource(id = if (isSelected) item.selectedIcon else item.unselectedIcon),
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
    val selectedIcon: Int,
    val unselectedIcon: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderWithFilterTabs(
    modifier: Modifier = Modifier,
    title: String,
    hasFilterTabs: Boolean = false,
    selectedTabIndex: Int = 0,
    tabs: List<TabItem> = emptyList(),
    onBackButtonPressed: () -> Unit = {}
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    Column {

        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.tertiary,
                navigationIconContentColor = MaterialTheme.colorScheme.tertiary
            ),
            title = {
                AnimatedVisibility(visible = visible, enter = slideInVertically(
                    animationSpec = tween(durationMillis = 1100),
                    initialOffsetY = {
                        it
                    }
                )
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.tertiary,
                            fontWeight = if (hasFilterTabs) FontWeight.Bold else FontWeight.Medium
                        )
                    )
                }
            },
            navigationIcon = {
                AnimatedVisibility(
                    visible = visible, enter = slideInHorizontally(
                        animationSpec = tween(durationMillis = 1000)
                    )
                ) {
                    IconButton(
                        onClick = onBackButtonPressed
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowLeft,
                            contentDescription = "Go back",
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(64.dp)
                        )


                    }
                }
            }

        )


        if (hasFilterTabs) {
            AnimatedVisibility(visible = visible, enter = slideInHorizontally(
                 animationSpec = tween(durationMillis = 800),
                 initialOffsetX = {
                     it / 2
                 }

                )
            ) {
                TabBarWithBadges()
            }

        }


    }
}

@Composable
fun TabBarWithBadges() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val tabWidth = screenWidth / 3

    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        TabItem("All", 12, true),
        TabItem("Completed", 5, false),
        TabItem("In progress", 3, false),
        TabItem("Pending Order", 0, false),
        TabItem("Cancelled", 0, false),
        // Assuming pending has no count visible
    )

    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.primary, // Purple background
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .padding(horizontal = if (selectedTab == 0) 16.dp else 4.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    height = 3.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .padding(horizontal = 8.dp)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = tab.title,
                            color = if (selectedTab == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inverseOnSurface,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1
                        )

                        if (tab.count > 0) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Badge(
                                count = tab.count,
                                isSelected = selectedTab == index
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Badge(
    count: Int,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 4.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = count.toString(),
            color = if (isSelected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.inverseOnSurface,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
fun TopBarComponent(
    userProfileImage: String = "", // URL or resource for profile image
    location: String = "Wertheimer, Illinois",
    onLocationClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onSearchTextChange: (String) -> Unit = {},
    searchText: String = "",
    onScanClick: () -> Unit = {}
) {

    var firstTimeBar by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        firstTimeBar = true
    }

    AnimatedVisibility(visible = firstTimeBar, enter = slideInVertically(
        animationSpec = tween(1000)
       )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 16.dp)
                .animateContentSize()
        ) {

            HomeProfileBar(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                userProfileImage = userProfileImage,
                location = location,
                onLocationClick = onLocationClick,
                onNotificationClick = onNotificationClick,
                onSearchTextChange = onSearchTextChange,
                searchText = searchText,
                onScanClick = onScanClick,
                onSearchOpen = {
                    // notify others
                },
                onSearchClosed = {
                    // notify
                }
            )

//        HeaderWithFilterTabs(
//            title = "Ship Payemts",
//            hasFilterTabs = true
//        )

//        HeaderWithFilterTabs(
//            title = "Calculate",
//            hasFilterTabs = false
//        )


        }

    }
}

// Top bar with profile, location and notification
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeProfileBar(
    modifier: Modifier = Modifier,
    userProfileImage: String = "",
    location: String,
    onNotificationClick: () -> Unit,
    onLocationClick: () -> Unit,
    onSearchTextChange: (String) -> Unit = {},
    searchText: String = "",
    onScanClick: () -> Unit = {},
    onSearchOpen: () -> Unit = {}, // ← callback when search starts,
    onSearchClosed: () -> Unit = {} // ← callback when search closed
) {
    var searchValue by remember { mutableStateOf(searchText) }
    var isSearchInProgress by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        // animate to hidden when focus is on search

        AnimatedVisibility(visible = !isSearchInProgress) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile and location section
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onLocationClick() }
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    // Profile image
                    AsyncImage(
                        model = if (userProfileImage.isNotEmpty()) userProfileImage else R.drawable.ic_profile_sample,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Location text and icon
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = "Your location",
                                tint = lightPurple,
                                modifier = Modifier.size(16.dp)
                            )

                            Spacer(modifier = Modifier.width(2.dp))

                            Text(
                                text = "Your location",
                                color = lightPurple,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = location,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Expand location",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                // Notification bell
                IconButton(
                    onClick = onNotificationClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .fillMaxWidth(0.1f)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notifications",
                        tint = darkBlue,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }

//        if (!isSearchInProgress) {
            Spacer(modifier = Modifier.height(if(!isSearchInProgress) 16.dp else 24.dp))
//        }


        // Search bar

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusRequester = remember { FocusRequester() }
        var isFocused by remember { mutableStateOf(false) }

        val imeVisible = WindowInsets.isImeVisible

        // This will be triggered when the keyboard becomes visible while the text field is focused
        LaunchedEffect(isFocused) {
            if (isFocused && !isSearchInProgress) {
                onSearchOpen()
                isSearchInProgress = true
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(0.1f),
                visible = isSearchInProgress,
                enter = slideInHorizontally(
                    animationSpec = tween(durationMillis = 1000)
                )
            ) {
                IconButton(onClick = {
                    onSearchClosed()
                    isSearchInProgress = false

                    focusManager.clearFocus()
                    keyboardController?.hide()


                }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(64.dp)
                    )


                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    BasicTextField(
                        value = searchValue,
                        onValueChange = {
                            searchValue = it
                            onSearchTextChange(it)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .onFocusChanged {
                                isFocused = it.isFocused
                            }
                            .padding(vertical = 4.dp),
                        textStyle = MaterialTheme.typography.bodyLarge.copy(
                            color = darkGray
                        ),
                        decorationBox = { innerTextField ->
                            if (searchValue.isEmpty()) {
                                Text(
                                    text = "Enter the receipt number ...",
                                    color = darkGray,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            innerTextField()
                        },
                        singleLine = true
                    )

                    // Scan button
                    IconButton(
                        onClick = onScanClick,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = secondary,
                                shape = CircleShape
                            )
                    ) {
                        ScanIcon()
                    }
                }
            }
        }

    }


}

// Preview
@Preview(showBackground = true)
@Composable
fun LocationHeaderPreview() {
    UitakehomeappTheme {
        TopBarComponent()
    }
}

// Alternative scan icon if you don't have ic_scan
@Composable
fun ScanIcon(
    modifier: Modifier = Modifier,
    tint: Color = Color.White
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_qr),
        contentDescription = "Scan",
        tint = tint,
        modifier = modifier
    )
}

// Tab with badges data class
data class TabItem(
    val title: String,
    val count: Int,
    val isSelected: Boolean
)

@Preview
@Composable
fun BottomBarPreview() {
    UitakehomeappTheme {
        BottomBar()
    }
}

@Preview(showBackground = true)
@Composable
fun TabBarPreview() {
    UitakehomeappTheme {
        TabBarWithBadges()
    }
}