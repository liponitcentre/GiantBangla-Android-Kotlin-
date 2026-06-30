package com.example.giantbangla.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Border
import com.example.giantbangla.home.BottomIcon
import com.example.giantbangla.ui.theme.BorderColor
import com.example.giantbangla.ui.theme.CardBg
import com.example.giantbangla.ui.theme.Green
import com.example.giantbangla.ui.theme.GreenDark
import com.example.giantbangla.ui.theme.ScreenBg
import com.example.giantbangla.ui.theme.Strike
import com.example.giantbangla.ui.theme.TextHint
import com.example.giantbangla.ui.theme.TextPrimary
import com.example.giantbangla.ui.theme.TextSecond


/* ------------------------------------------------------------------ */
/*  Data                                                               */
/* ------------------------------------------------------------------ */
data class MiniProduct(
    val name: String = "Walton Elite Book",
    val rating: String = "4.5",
    val reviews: String = "(19k)",
    val sold: String = "Sold:50/17",
    val price: String = "৳30,070",
    val oldPrice: String = "৳400",
    val discount: String = "15% Off",
)

data class Review(
    val name: String,
    val time: String,
    val text: String,
)

private val similar = List(2) { MiniProduct() }
private val reviews = listOf(
    Review("Darrell Steward", "21 mins ago",
        "Vivamus fermentum consectetur ligula vel tristique. Nulla nec quam ultricies, bibendum quam sem quis, sollicitudin quam. In gravida tempor faucibus. Curabitur at accumsan eros. Suspendisse cursus velit non metus posuere, quis rhoncus velit volutpat."),
    Review("Dianne Russell", "Just now",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ullamcorper ut lectus nec tincidunt. Nunc mattis dignissim arcu, sit amet consequat sem auctor a."),
)

/* ================================================================== */
/*  Screen                                                             */
/* ================================================================== */
@Composable
fun ProductDetailsScreen(
    onBack: () -> Unit = {},
    onBuyNow: () -> Unit = {},
    onAddToCart: () -> Unit = {},
    onStore: () -> Unit = {},
    onChat: () -> Unit = {},
) {
    Scaffold(
        containerColor = ScreenBg,
        bottomBar = {
            BottomActionBar(
                onStore = onStore,
                onChat = onChat,
                onBuyNow = onBuyNow,
                onAddToCart = onAddToCart,
            )
        },
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            TopSearchRow()
            StoreRow()
            ProductGallery()
            ProductTitleBlock()
            RatingPriceRow()
            HotDealsTag()
            DiscountPriceRow()
            Spacer(Modifier.height(12.dp))
            ActionRow(Icons.Outlined.LocalShipping, "Delivery by 1 sep,Monday")
            ActionRow(Icons.Outlined.LocalOffer, "All offers & Coupons")

            SectionTitleRow("Similar products")
            MiniProductPair(similar)

            Spacer(Modifier.height(16.dp))
            BadgeCardsRow()
            WarrantyBar()

            ChevronRow("Product Details", bold = true)
            HighlightsBlock()

            ChevronRow("All Details")
            ChevronRow("About the product")
            ChevronRow("Similar products of walton")
            MiniProductPair(similar)

            Spacer(Modifier.height(16.dp))
            SectionTitleRow("Discount of similar product")
            MiniProductPair(similar)

            Spacer(Modifier.height(16.dp))
            RatingReviewsBlock()
            ReviewsList()

            ChevronRow("All Questions")
            SellerBlock()
            ChevronRow("See other sellers")

            SectionLabel("Similar Laptops")
            MiniProductPairWithArrows(similar)

            SectionLabel("Recommendations")
            MiniProductPair(similar)
            Spacer(Modifier.height(16.dp))
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Top: search + camera                                               */
/* ------------------------------------------------------------------ */
@Composable
private fun TopSearchRow() {
    var query by remember { mutableStateOf("LapTop") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .height(46.dp)
                .background(White, RoundedCornerShape(10.dp))
                //.border(1.dp, Border, RoundedCornerShape(10.dp))
                .padding(horizontal = 12.dp),
        ) {
            Icon(Icons.Default.Search, null, tint = TextSecond, modifier = Modifier.size(20.dp))
            BasicTextField(
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                textStyle = TextStyle(color = TextPrimary, fontSize = 15.sp),
                modifier = Modifier.weight(1f).padding(start = 8.dp),
            )
            Icon(Icons.Default.Close, "Clear", tint = TextSecond, modifier = Modifier.size(18.dp).clickable { query = "" })
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(46.dp)
                .background(White, RoundedCornerShape(10.dp))
                //.border(1.dp, Border, RoundedCornerShape(10.dp)),
        ) {
            Icon(Icons.Default.PhotoCamera, "Search by image", tint = TextPrimary, modifier = Modifier.size(20.dp))
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Store row: Walton | Visit Store + wishlist/share                   */
/* ------------------------------------------------------------------ */
@Composable
private fun StoreRow() {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        Text("Walton", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { }) {
                Text("Visit Store", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TextSecond, modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.height(10.dp))
            Icon(Icons.Default.FavoriteBorder, "Wishlist", tint = TextSecond, modifier = Modifier.size(22.dp).clickable { })
            Spacer(Modifier.height(10.dp))
            Icon(Icons.Default.Share, "Share", tint = TextSecond, modifier = Modifier.size(22.dp).clickable { })
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Image gallery + dots                                               */
/* ------------------------------------------------------------------ */
@Composable
private fun ProductGallery() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        // Product image placeholder — replace with painterResource(R.drawable.laptop)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().height(190.dp),
        ) {
            Icon(Icons.Default.Laptop, "Walton Prelude N50", tint = Color(0xFF6E7B8A), modifier = Modifier.size(120.dp))
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            repeat(4) { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == 0) 8.dp else 6.dp)
                        .background(if (i == 0) Green else Color(0xFFBFC5C5), CircleShape),
                )
            }
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Title + spec description                                           */
/* ------------------------------------------------------------------ */
@Composable
private fun ProductTitleBlock() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text("Walton Prelude N50", color = TextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text(
            "15(2025) AMD Athlon Dual Core 7120U-(8GB 256 GB SSD/Windows 11 Home) 15 thin and Light Laptop(15.6 inch, Turbo ",
            color = TextSecond,
            fontSize = 13.sp,
            lineHeight = 19.sp,
        )
        Text("more......", color = TextSecond, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { })
    }
}

/* ------------------------------------------------------------------ */
/*  Rating + "Excellent" + price                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun RatingPriceRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        repeat(5) { Icon(Icons.Default.Star, null, modifier = Modifier.size(15.dp)) }
        Text(" (10)", color = TextSecond, fontSize = 12.sp)
        Spacer(Modifier.width(8.dp))
        Text("Excellent", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Spacer(Modifier.weight(1f))
        Text("৳ 24,500", color = Green, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

/* ------------------------------------------------------------------ */
/*  Hot Deals tag (banner shape)                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun HotDealsTag() {
    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Box(
            modifier = Modifier
                .background(Green, RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp, bottomEnd = 6.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp),
        ) {
            Text("Hot Deals", color = White, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Discounted price row                                                */
/* ------------------------------------------------------------------ */
@Composable
private fun DiscountPriceRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        Text("৳1699", color = Green, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(8.dp))
        Text("৳1999.00", color = Strike, fontSize = 14.sp, textDecoration = TextDecoration.LineThrough)
        Spacer(Modifier.width(12.dp))
        Box(
            modifier = Modifier
                .background(Green, RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 5.dp),
        ) {
            Text("21% OFF", color = White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Bordered action rows (delivery / offers)                           */
/* ------------------------------------------------------------------ */
@Composable
private fun ActionRow(icon: ImageVector, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(White, RoundedCornerShape(8.dp))
            //.border(1.dp, Border, RoundedCornerShape(8.dp))
            .clickable { }
            .padding(horizontal = 14.dp, vertical = 14.dp),
    ) {
        Icon(icon, null, tint = TextPrimary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(12.dp))
        Text(label, color = TextPrimary, fontSize = 14.sp, modifier = Modifier.weight(1f))
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TextSecond, modifier = Modifier.size(22.dp))
    }
}

/* ------------------------------------------------------------------ */
/*  Section title with trailing chevron                                */
/* ------------------------------------------------------------------ */
@Composable
private fun SectionTitleRow(title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Text(title, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.weight(1f))
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TextSecond, modifier = Modifier.size(22.dp).clickable { })
    }
}

@Composable
private fun SectionLabel(title: String) {
    Text(
        title,
        color = TextPrimary,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
    )
}

/* ------------------------------------------------------------------ */
/*  Plain chevron row (Product Details, All Details, ...)              */
/* ------------------------------------------------------------------ */
@Composable
private fun ChevronRow(label: String, bold: Boolean = false) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().clickable { }.padding(horizontal = 16.dp, vertical = 14.dp),
        ) {
            Text(
                label,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.weight(1f),
            )
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = TextSecond, modifier = Modifier.size(22.dp))
        }
        HorizontalDivider(color = BorderColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
    }
}

/* ------------------------------------------------------------------ */
/*  Two badge cards: replacement / cash on delivery                    */
/* ------------------------------------------------------------------ */
@Composable
private fun BadgeCardsRow() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    ) {
        BadgeCard(Icons.Default.Autorenew, "7 days", "Replacement", Modifier.weight(1f))
        BadgeCard(Icons.Default.Payments, "Cash on Delivery", "available", Modifier.weight(1f))
    }
}

@Composable
private fun BadgeCard(icon: ImageVector, line1: String, line2: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(White, RoundedCornerShape(10.dp))
            //.border(1.dp, Border, RoundedCornerShape(10.dp))
            .padding(12.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(34.dp).background(Green, CircleShape),
        ) { Icon(icon, null, tint = White, modifier = Modifier.size(18.dp)) }
        Spacer(Modifier.width(10.dp))
        Column {
            Text(line1, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(line2, color = TextSecond, fontSize = 12.sp)
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Warranty bar                                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun WarrantyBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(White, RoundedCornerShape(8.dp))
            //.border(1.dp, Border, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp),
    ) {
        // Walton logo placeholder
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(width = 48.dp, height = 24.dp).background(Color(0xFF0A3D91), RoundedCornerShape(3.dp)),
        ) { Text("WALTON", color = White, fontSize = 7.sp, fontWeight = FontWeight.Bold) }
        Spacer(Modifier.width(10.dp))
        Text("On site 1 year warranty", color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.width(6.dp))
        Text("Know more", color = Green, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable { })
    }
}

/* ------------------------------------------------------------------ */
/*  Highlights block (spec list)                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun HighlightsBlock() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text("Highlights", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        SpecRow(
            labels = listOf("Processor"),
            icon = Icons.Default.Memory,
            value = "AMD Athlon Dual Core.",
            note = "Smooth gaming and video editing Exp..",
        )
        SpecRow(
            labels = listOf("RAM", "SSD Storage"),
            icon = Icons.Default.SdStorage,
            value = "8 GB | 256 GB",
            note = "Smooth gaming and video editing Exp..",
        )
        SpecRow(
            labels = listOf("Screen Size", "Display", "Refresh Rate"),
            icon = Icons.Default.DesktopWindows,
            value = "21.5 Inc Display | HD Display | 60 Hz",
            note = "Good Visual Quality",
        )
        // Highlighted (blue-bordered) Graphics spec
        Box(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth()
                .border(1.5.dp, Color.Blue, RoundedCornerShape(8.dp))
                .padding(12.dp),
        ) {
            SpecRow(
                labels = listOf("Graphics"),
                icon = Icons.Default.Videocam,
                value = "AMD Radeon",
                note = "Powerful graphics for immersive Experience",
                topPadding = false,
            )
        }
        SpecRow(
            labels = listOf("Weight"),
            icon = Icons.Default.FitnessCenter,
            value = "1.5 kg",
            note = "Lightweight and easy to carry",
        )
    }
}

@Composable
private fun SpecRow(
    labels: List<String>,
    icon: ImageVector,
    value: String,
    note: String,
    topPadding: Boolean = true,
) {
    Column(modifier = Modifier.padding(top = if (topPadding) 14.dp else 0.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            labels.forEachIndexed { i, l ->
                if (i > 0) Text(" | ", color = Strike, fontSize = 13.sp)
                Text(l, color = Green, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = TextPrimary, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
            Text(value, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(4.dp))
        Text(note, color = TextSecond, fontSize = 13.sp, lineHeight = 18.sp)
    }
}

/* ------------------------------------------------------------------ */
/*  Mini product card + pairs                                          */
/* ------------------------------------------------------------------ */
@Composable
private fun MiniProductPair(items: List<MiniProduct>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        items.take(2).forEach { MiniProductCard(it, Modifier.weight(1f)) }
    }
}

@Composable
private fun MiniProductPairWithArrows(items: List<MiniProduct>) {
    Column {
        MiniProductPair(items)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        ) {
            ArrowButton(Icons.AutoMirrored.Filled.KeyboardArrowLeft)
            ArrowButton(Icons.AutoMirrored.Filled.KeyboardArrowRight)
        }
    }
}

@Composable
private fun ArrowButton(icon: ImageVector) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(28.dp).clickable { },
    ) { Icon(icon, null, tint = TextPrimary, modifier = Modifier.size(20.dp)) }
}

@Composable
private fun MiniProductCard(p: MiniProduct, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(CardBg, RoundedCornerShape(10.dp)).padding(10.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth().height(100.dp),
            ) { Icon(Icons.Default.Laptop, p.name, tint = Color(0xFF8AA0C0), modifier = Modifier.size(58.dp)) }
            Box(
                modifier = Modifier.align(Alignment.TopStart)
                    .background(Green, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            ) { Text(p.discount, color = White, fontSize = 11.sp, fontWeight = FontWeight.SemiBold) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.TopEnd),
            ) {
                MiniCircleIcon(Icons.Default.FavoriteBorder)
                MiniCircleIcon(Icons.Default.Visibility)
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(p.name, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(p.rating, color = TextSecond, fontSize = 12.sp)
            Icon(Icons.Default.Star, null, modifier = Modifier.size(12.dp).padding(horizontal = 1.dp))
            Text(p.reviews, color = TextSecond, fontSize = 12.sp)
            Spacer(Modifier.weight(1f))
            Text(p.sold, color = TextSecond, fontSize = 10.sp)
        }
        Spacer(Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(p.price, color = Green, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.width(6.dp))
            Text(p.oldPrice, color = Strike, fontSize = 12.sp, textDecoration = TextDecoration.LineThrough)
        }
    }
}

@Composable
private fun MiniCircleIcon(icon: ImageVector) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(26.dp).background(White, CircleShape),
    ) { Icon(icon, null, tint = TextPrimary, modifier = Modifier.size(15.dp)) }
}

/* ------------------------------------------------------------------ */
/*  Rating & Reviews summary                                           */
/* ------------------------------------------------------------------ */
@Composable
private fun RatingReviewsBlock() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Rating & Reviews", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .border(1.dp, Green, RoundedCornerShape(6.dp))
                    .clickable { }
                    .padding(horizontal = 14.dp, vertical = 7.dp),
            ) { Text("Rate Product", color = Green, fontSize = 13.sp, fontWeight = FontWeight.SemiBold) }
        }
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.Top) {
            Column(modifier = Modifier.width(80.dp)) {
                Text("4.3", color = TextPrimary, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                Text("23 ratings & 247 reviews", color = TextHint, fontSize = 12.sp, lineHeight = 16.sp)
            }
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                val bars = listOf(5 to 0.95f, 4 to 0.45f, 3 to 0.35f, 2 to 0.18f, 1 to 0f)
                val counts = listOf("12", "5", "4", "2", "0")
                bars.forEachIndexed { i, (stars, frac) ->
                    RatingBar(stars, frac, counts[i])
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            listOf("Performance", "Battery", "Display", "Design").forEach { SubRating(it) }
        }
    }
}

@Composable
private fun RatingBar(stars: Int, fraction: Float, count: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 2.dp)) {
        repeat(stars) { Icon(Icons.Default.Star, null, modifier = Modifier.size(11.dp)) }
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier.weight(1f).height(7.dp).background(Color(0xFFE3E6E6), RoundedCornerShape(4.dp)),
        ) {
            if (fraction > 0f) {
                Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(fraction).background(Green, RoundedCornerShape(4.dp)))
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(count, color = TextSecond, fontSize = 12.sp, modifier = Modifier.width(18.dp))
    }
}

@Composable
private fun SubRating(label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(48.dp).border(2.dp, Green, CircleShape),
        ) { Text("4.3", color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold) }
        Spacer(Modifier.height(6.dp))
        Text(label, color = TextSecond, fontSize = 12.sp)
    }
}

/* ------------------------------------------------------------------ */
/*  Reviews list                                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun ReviewsList() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
        reviews.forEach { r ->
            Row(modifier = Modifier.padding(vertical = 10.dp)) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(34.dp).background(Color(0xFFD9DEDE), CircleShape),
                ) { Icon(Icons.Default.Person, null, tint = White, modifier = Modifier.size(20.dp)) }
                Spacer(Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(r.name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                        Text("  •  ${r.time}", color = TextHint, fontSize = 12.sp)
                    }
                    Spacer(Modifier.height(4.dp))
                    Row { repeat(5) { Icon(Icons.Default.Star, null, modifier = Modifier.size(12.dp)) } }
                    Spacer(Modifier.height(6.dp))
                    Text(r.text, color = TextSecond, fontSize = 13.sp, lineHeight = 19.sp)
                }
            }
            HorizontalDivider(color = BorderColor, thickness = 1.dp)
        }
    }
}

/* ------------------------------------------------------------------ */
/*  Seller block                                                       */
/* ------------------------------------------------------------------ */
@Composable
private fun SellerBlock() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(40.dp).background(Green, CircleShape),
            ) { Icon(Icons.Default.Store, null, tint = White, modifier = Modifier.size(22.dp)) }
            Spacer(Modifier.width(10.dp))
            Column {
                Text("TBL Online", color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { Icon(Icons.Default.Star, null,  modifier = Modifier.size(12.dp)) }
                    Text(" 5.0  .4 years in Giant BanglaApp", color = TextSecond, fontSize = 12.sp)
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.border(1.dp, Border, RoundedCornerShape(10.dp))
                .padding(vertical = 16.dp),
        ) {
            SellerStat("20k+", "Product Sold", Modifier.weight(1f))
            SellerStat("86%", "Quality Score", Modifier.weight(1f))
            SellerStat("86%", "Speed Score", Modifier.weight(1f))
        }
    }
}

@Composable
private fun SellerStat(value: String, label: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(value, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(label, color = TextSecond, fontSize = 12.sp)
    }
}

/* ------------------------------------------------------------------ */
/*  Fixed bottom action bar                                            */
/* ------------------------------------------------------------------ */
@Composable
private fun BottomActionBar(
    onStore: () -> Unit,
    onChat: () -> Unit,
    onBuyNow: () -> Unit,
    onAddToCart: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Green)
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        BottomIcon(Icons.Default.Store, "Store", onStore)
        Spacer(Modifier.width(20.dp))
        BottomIcon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "Chat", onChat)
        Spacer(Modifier.weight(1f))
        OutlinedPill("Buy Now", onBuyNow)
        Spacer(Modifier.width(12.dp))
        FilledPill("Add to Cart", onAddToCart)
    }
}

@Composable
private fun BottomIcon(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(onClick = onClick)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(34.dp).background(GreenDark, CircleShape),
        ) { Icon(icon, label, tint = White, modifier = Modifier.size(18.dp)) }
        Spacer(Modifier.height(2.dp))
        Text(label, color = White, fontSize = 10.sp)
    }
}

@Composable
private fun OutlinedPill(text: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(40.dp)
            .border(1.dp, White, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp),
    ) { Text(text, color = White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold) }
}

@Composable
private fun FilledPill(text: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(40.dp)
            .background(White, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 22.dp),
    ) { Text(text, color = Green, fontSize = 14.sp, fontWeight = FontWeight.SemiBold) }
}
