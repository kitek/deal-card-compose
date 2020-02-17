package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.ui.core.Clip
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.text.style.TextDecoration
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import pl.kitek.dealcard.R
import pl.kitek.dealcard.data.deal1
import pl.kitek.dealcard.data.deal2
import pl.kitek.dealcard.model.*
import pl.kitek.dealcard.ui.VectorImage
import pl.kitek.dealcard.ui.dealCardFontFamily

@Composable
fun DealCard(deal: BasicDeal) {
    Ripple(bounded = true) {
        Column {
            Stack {
                DealCardImage(
                    mainImageRes = deal.mainImageResId,
                    partnerImageRes = deal.partnerLogoResId
                )
                DealPrice(deal.price, modifier = LayoutGravity.BottomRight)
            }
            DealNames(deal.title, deal.subtitle, deal.isFavourite)
            DealSellingPoints(deal.usps)
            DealSearchTags(deal.searchTags)
        }
    }
}

@Composable
fun DealCardImage(
    mainImageRes: Int,
    partnerImageRes: Int
) {
    Stack(
        modifier = LayoutWidth.Fill + LayoutPadding(
            left = 16.dp,
            top = 16.dp,
            right = 16.dp,
            bottom = 4.dp
        )
    ) {
        Container(modifier = LayoutAspectRatio(1.5f)) {
            Clip(shape = RoundedCornerShape(4.dp)) {
                val mainImage = imageResource(mainImageRes)
                DrawImage(image = mainImage)
            }
        }
        PartnerLogoImage(partnerImageRes, modifier = LayoutGravity.TopLeft)
    }
}

@Composable
fun PartnerLogoImage(partnerImageRes: Int, modifier: Modifier = Modifier.None) {
    val partnerLogo = if (partnerImageRes > 0) imageResource(partnerImageRes) else null
    partnerLogo?.let {
        val inputWidth = partnerLogo.width.toFloat()
        val inputHeight = partnerLogo.height.toFloat()
        val inputAspectRatio = inputWidth / inputHeight

        val height = 48.dp
        val width = height * inputAspectRatio

        Container(
            modifier = modifier + LayoutSize(
                width = width,
                height = height
            ) + LayoutPadding(left = 16.dp, top = 16.dp)
        ) {
            DrawImage(image = partnerLogo)
        }
    }
}

@Composable
fun DealPrice(price: DealPrice, modifier: Modifier = Modifier.None) {
    val oldPriceTextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        color = Color.White,
        textDecoration = TextDecoration.LineThrough
    )
    val newPriceTextStyle = TextStyle(
        fontSize = 18.sp,
        fontFamily = dealCardFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    Surface(
        color = Color.Red,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier + LayoutPadding(right = 12.dp)
    ) {
        Row(
            modifier = LayoutPadding(left = 16.dp, right = 16.dp, top = 8.dp, bottom = 8.dp),
            arrangement = Arrangement.Center
        ) {
            Text(
                price.oldPrice,
                style = oldPriceTextStyle,
                modifier = LayoutPadding(right = 4.dp, top = 3.dp)
            )
            Text(price.newPrice, style = newPriceTextStyle, modifier = LayoutPadding(left = 4.dp))
        }
    }
}

@Composable
fun DealNames(title: String, subtitle: String, isFavourite: Boolean) {
    val titleStyle = ((MaterialTheme.typography()).subtitle1)
    val subTitleStyle = ((MaterialTheme.typography()).subtitle2)

    Row(modifier = LayoutPadding(left = 16.dp, right = 4.dp) + LayoutWidth.Fill) {
        Column(modifier = LayoutPadding(top = 11.dp) + LayoutFlexible(1f)) {
            Text(title, style = titleStyle)
            Text(
                subtitle,
                style = subTitleStyle,
                modifier = LayoutPadding(top = 2.dp, bottom = 4.dp)
            )
        }
        DealFavourite(isFavourite)
    }
}

@Composable
fun DealFavourite(isFavourite: Boolean) {
    val iconRes = if (isFavourite) {
        R.drawable.ic_baseline_favorite_24
    } else {
        R.drawable.ic_baseline_favorite_border_24
    }
    val favIcon = vectorResource(iconRes)

    Ripple(bounded = true) {
        Padding(padding = 12.dp) {
            Container(modifier = LayoutSize(width = 24.dp, height = 24.dp)) {
                DrawVector(vectorImage = favIcon)
            }
        }
    }
}

@Composable
fun DealSellingPoints(usps: List<UniqueSellingPoint>) {
    if (usps.isEmpty()) return

    Row(
        arrangement = Arrangement.Begin,
        modifier = LayoutPadding(
            left = 8.dp,
            right = 8.dp,
            top = 4.dp,
            bottom = 8.dp
        ) + LayoutWidth.Fill
    ) {
        usps.forEach {
            when (it) {
                is BoughtUsp -> DealDealBoughtSellingPoint(it.amount)
                is SaveUsp -> DealSaveSellingPoint(it.amount)
            }
        }
    }
}

@Composable
fun DealDealBoughtSellingPoint(amount: String) {
    Row(modifier = LayoutPadding(left = 8.dp, right = 8.dp)) {
        val icon = vectorResource(R.drawable.ic_baseline_check_24)
        Container(modifier = LayoutSize(width = 18.dp, height = 18.dp)) {
            DrawVector(vectorImage = icon)
        }
        Text(
            "$amount köpta",
            modifier = LayoutPadding(left = 8.dp),
            style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
        )
    }
}

@Composable
fun DealSaveSellingPoint(amount: Int) {
    Row(modifier = LayoutPadding(left = 8.dp, right = 8.dp)) {
        val icon = vectorResource(R.drawable.ic_baseline_new_releases_24)
        Container(modifier = LayoutSize(width = 18.dp, height = 18.dp)) {
            DrawVector(vectorImage = icon)
        }
        Text(
            "Spara $amount%",
            modifier = LayoutPadding(left = 8.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF48c55c)
            )
        )
    }
}

@Composable
fun DealSearchTags(searchTags: List<String>) {
    if (searchTags.isEmpty()) return

    Row(
        modifier = LayoutPadding(
            left = 8.dp,
            right = 8.dp,
            top = 8.dp,
            bottom = 8.dp
        ) + LayoutWidth.Fill
    ) {
        searchTags.forEach { DealSearchTag(it) }
    }
}

@Composable
fun DealSearchTag(name: String) {
    Surface(
        borderWidth = 1.dp,
        borderBrush = SolidColor(Color(0xb3000000)),
        shape = RoundedCornerShape(16.dp),
        modifier = LayoutPadding(left = 4.dp, right = 4.dp)
    ) {
        Ripple(bounded = true) {
            Padding(left = 8.dp, right = 8.dp, top = 7.dp, bottom = 7.dp) {
                Row {
                    VectorImage(
                        R.drawable.ic_baseline_search_24,
                        LayoutSize(width = 18.dp, height = 18.dp)
                    )
                    Text(
                        name,
                        style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif),
                        modifier = LayoutPadding(left = 4.dp, right = 4.dp, bottom = 1.dp)
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    DealCard(deal = deal2)
}

@Preview()
@Composable
fun DealNamesPreview() {
    DealNames(deal1.title, deal2.subtitle, true)
}

@Preview
@Composable
fun DealSellingPointsPreview() {
    DealSellingPoints(deal1.usps)
}
