package com.example.giantbangla.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Kitchen
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.giantbangla.ui.theme.BannerGreen
import com.example.giantbangla.ui.theme.BorderColor
import com.example.giantbangla.ui.theme.CardBg
import com.example.giantbangla.ui.theme.CatBlack
import com.example.giantbangla.ui.theme.CatGray
import com.example.giantbangla.ui.theme.CatLightGreen
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.ScreenBg
import com.example.giantbangla.ui.theme.StrikeColor
import com.example.giantbangla.ui.theme.TextPrimary
import com.example.giantbangla.ui.theme.TextSecond
import com.example.giantbangla.ui.theme.TextSecondary
import kotlinx.coroutines.delay


/* ------------------------------------------------------------------ */
/*  Bottom-nav model                                                   */
/* ------------------------------------------------------------------ */
enum class HomeTab(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Outlined.Home),
    Message("Message", Icons.Outlined.MailOutline),
    Cart("Cart", Icons.Outlined.ShoppingCart),
    Profile("Profile", Icons.Outlined.Person),
}

/* ------------------------------------------------------------------ */
/*  Data                                                               */
/* ------------------------------------------------------------------ */
data class Product(
    val name: String,
    val rating: String,
    val reviews: String,
    val sold: String,
    val price: String,
    val oldPrice: String,
    val discount: String,
)

private val sampleProducts = List(2) {
    Product("Walton Elite Book", "4.5", "(19k)", "Sold:50/17", "৳30,070", "৳400", "15% Off")
}

private data class Category(val label: String, val icon: ImageVector, val bg: Color, val tint: Color)

private val categories = listOf(
    Category("Fashion", Icons.Default.Checkroom, CatBlack, Color.White),
    Category("Electronics", Icons.Default.Laptop, CatGray, Color.White),
    Category("Appliances", Icons.Default.Kitchen, CatLightGreen, Green),
    Category("Beauty", Icons.Default.Spa, CatLightGreen, Green),
    Category("Furniture", Icons.Default.Weekend, CatLightGreen, Green),
)

/* ================================================================== */
/*  Screen                                                             */
/* ================================================================== */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    selectedTab: HomeTab = HomeTab.Home,
    onTabSelected: (HomeTab) -> Unit = {},
    onLogin: () -> Unit = {},
) {
    Scaffold(
        containerColor = ScreenBg,
        topBar = { HomeTopBar() },
        bottomBar = { HomeBottomBar(selectedTab, onTabSelected) },
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            SearchBar()
            PromoBanner()
            SectionHeader("Categories", "View All")
            CategoriesRow()
            DealOfTheDay()
            ProductPair(sampleProducts)
            Spacer(Modifier.height(16.dp))
            TopOffersCard()
            FilterChipsRow()
            ProductPair(sampleProducts)
            Spacer(Modifier.height(12.dp))
            LoginPromptBar(onLogin)
            Spacer(Modifier.height(8.dp))
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Top bar                                                            */
/* ------------------------------------------------------------------ */
@Composable
private fun HomeTopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.safeDrawingPadding()
            .background(ScreenBg)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = TextPrimary)
        Text(
            text = "Giant BanglaApp",
            color = Green,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            modifier = Modifier.weight(1f),
        )
        Icon(Icons.Outlined.Notifications, contentDescription = "Notifications", tint = TextPrimary)
    }
}

/* ------------------------------------------------------------------ */
/*  Search                                                             */
/* ------------------------------------------------------------------ */
@Composable
private fun SearchBar() {
    var query by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.dp, BorderColor, RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp),
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary)
            BasicSearchField(
                value = query,
                onValueChange = { query = it },
                placeholder = "Search",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.dp, BorderColor, RoundedCornerShape(10.dp)),
        ) {
            Icon(Icons.Default.PhotoCamera, contentDescription = "Search by image", tint = TextPrimary)
        }
    }
}

@Composable
private fun BasicSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.text.BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        textStyle = androidx.compose.ui.text.TextStyle(color = TextPrimary, fontSize = 15.sp),
        modifier = modifier,
        decorationBox = { innerField ->
            if (value.isEmpty()) {
                Text(placeholder, color = TextSecondary, fontSize = 15.sp)
            }
            innerField()
        },
    )
}

/* ------------------------------------------------------------------ */
/*  Promo banner + dots                                                */
/* ------------------------------------------------------------------ */
@Composable
private fun PromoBanner() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(BannerGreen, RoundedCornerShape(14.dp)),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 18.dp, end = 140.dp),
            ) {
                Text(
                    "20% OFF DURING THE WEEKEND",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 22.sp,
                )
                Spacer(Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .clickable { }
                        .padding(horizontal = 18.dp, vertical = 8.dp),
                ) {
                    Text("Get Now", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                }
            }
            // Banner artwork placeholder — replace with:
            // Image(painterResource(R.drawable.banner_bags), null, Modifier.align(Alignment.CenterEnd))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(96.dp),
            ) {
                Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = Color.White.copy(alpha = 0.9f), modifier = Modifier.size(56.dp))
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth(),
        ) {
            repeat(3) { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == 0) 9.dp else 7.dp)
                        .background(if (i == 0) Green else Color(0xFFBFC5C5), CircleShape),
                )
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Section header (title + trailing link)                             */
/* ------------------------------------------------------------------ */
@Composable
private fun SectionHeader(title: String, action: String, onAction: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(title, color = Green, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Text(
            action,
            color = Green,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable(onClick = onAction),
        )
    }
}

/* ------------------------------------------------------------------ */
/*  Categories                                                         */
/* ------------------------------------------------------------------ */
@Composable
private fun CategoriesRow() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        categories.forEach { cat ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(56.dp)
                        .background(cat.bg, CircleShape),
                ) {
                    Icon(cat.icon, contentDescription = cat.label, tint = cat.tint, modifier = Modifier.size(26.dp))
                }
                Spacer(Modifier.height(6.dp))
                Text(cat.label, fontSize = 12.sp, color = TextPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Deal of the day + countdown                                        */
/* ------------------------------------------------------------------ */
@Composable
private fun DealOfTheDay() {
    var totalSeconds by remember { mutableIntStateOf(11 * 3600 + 15 * 60 + 4) }
    LaunchedEffect(Unit) {
        while (totalSeconds > 0) { delay(1000); totalSeconds-- }
    }
    val h = totalSeconds / 3600
    val m = (totalSeconds % 3600) / 60
    val s = totalSeconds % 60

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .border(1.dp, Green, RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Deal of the day", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { }) {
                Text("View All", color = TextSecond, fontSize = 13.sp)
                Icon(Icons.Default.ArrowForward, null, tint = TextSecond, modifier = Modifier.size(16.dp).padding(start = 2.dp))
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TimeUnit("%02d".format(h), "HRS")
            TimeUnit("%02d".format(m), "MIN")
            TimeUnit("%02d".format(s), "SEC")
        }
    }
}

@Composable
private fun TimeUnit(value: String, unit: String) {
    Row(verticalAlignment = Alignment.Bottom) {
        Text(value, color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(
            " $unit",
            color = TextSecond,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 2.dp),
        )
    }
}

/* ------------------------------------------------------------------ */
/*  Product card + pair row                                            */
/* ------------------------------------------------------------------ */
@Composable
private fun ProductPair(products: List<Product>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        products.take(2).forEach { p ->
            ProductCard(p, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(CardBg, RoundedCornerShape(10.dp))
            .padding(10.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Product image placeholder — replace with painterResource(R.drawable.product)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
            ) {
                Icon(Icons.Default.Laptop, contentDescription = product.name, tint = Color(0xFF8AA0C0), modifier = Modifier.size(64.dp))
            }
            // Discount badge
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .background(Green, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) {
                Text(product.discount, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
            // Heart + eye actions
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                CircleIcon(Icons.Default.FavoriteBorder)
                CircleIcon(Icons.Default.Visibility)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(product.name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(product.rating, color = TextSecond, fontSize = 12.sp)
            Icon(Icons.Default.Star, null, tint = Color(0xFFFFB300), modifier = Modifier.size(13.dp).padding(horizontal = 1.dp))
            Text(product.reviews, color = TextSecond, fontSize = 12.sp)
            Spacer(Modifier.weight(1f))
            Text(product.sold, color = TextSecond, fontSize = 11.sp)
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(product.price, color = Green, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(6.dp))
            Text(
                product.oldPrice,
                color = StrikeColor,
                fontSize = 12.sp,
                textDecoration = TextDecoration.LineThrough,
            )
        }
    }
}

@Composable
private fun CircleIcon(icon: ImageVector) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(28.dp)
            .background(Color.White, CircleShape),
    ) {
        Icon(icon, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(16.dp))
    }
}

/* ------------------------------------------------------------------ */
/*  Top offers card (2x2)                                              */
/* ------------------------------------------------------------------ */
@Composable
private fun TopOffersCard() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(14.dp))
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Top offers", color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("Start Shopping", color = Green, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { })
        }
        Spacer(Modifier.height(14.dp))
        repeat(2) { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                repeat(2) {
                    OfferTile(modifier = Modifier.weight(1f))
                }
            }
            if (row == 0) Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun OfferTile(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.fillMaxWidth().height(96.dp)) {
            // Offer cover placeholder
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE9EEF1), RoundedCornerShape(8.dp)),
            ) {
                Icon(Icons.Default.Image, null, tint = Color(0xFFB6C2CC), modifier = Modifier.size(32.dp))
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(Green, RoundedCornerShape(topStart = 6.dp))
                    .padding(horizontal = 8.dp, vertical = 3.dp),
            ) {
                Text("15% Off", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(Modifier.height(4.dp))
        Text("Limited time\ndeal", color = Green, fontSize = 12.sp, fontWeight = FontWeight.Medium, lineHeight = 15.sp)
    }
}

/* ------------------------------------------------------------------ */
/*  Filter chips                                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun FilterChipsRow() {
    val chips = listOf("For You", "Accessories", "Home & Kitchen", "Office supplies")
    var selected by remember { mutableStateOf(0) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        chips.forEachIndexed { i, label ->
            val active = i == selected
            Box(
                modifier = Modifier
                    .background(if (active) Green else Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, if (active) Green else BorderColor, RoundedCornerShape(8.dp))
                    .clickable { selected = i }
                    .padding(horizontal = 14.dp, vertical = 8.dp),
            ) {
                Text(label, color = if (active) Color.White else TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

// Simple horizontal scroll helper so chips don't clip on narrow screens.
//@Composable
//private fun Modifier.horizontalScrollIfNeeded(): Modifier =
//    this.then(androidx.compose.foundation.horizontalScroll(rememberScrollState()))

/* ------------------------------------------------------------------ */
/*  Login prompt bar                                                   */
/* ------------------------------------------------------------------ */
@Composable
private fun LoginPromptBar(onLogin: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(Green, RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
    ) {
        Text("Easy log in with Facebook/Google", color = Color.White, fontSize = 13.sp, modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(6.dp))
                .clickable(onClick = onLogin)
                .padding(horizontal = 16.dp, vertical = 6.dp),
        ) {
            Text("Log in", color = Green, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Bottom navigation                                                  */
/* ------------------------------------------------------------------ */
@Composable
private fun HomeBottomBar(selected: HomeTab, onSelected: (HomeTab) -> Unit) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        HomeTab.values().forEach { tab ->
            NavigationBarItem(
                selected = tab == selected,
                onClick = { onSelected(tab) },
                icon = { Icon(tab.icon, contentDescription = tab.label) },
                label = { Text(tab.label, fontSize = 11.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    selectedTextColor = Green,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = TextSecond,
                    unselectedTextColor = TextSecond,
                ),
            )
        }
    }
}
