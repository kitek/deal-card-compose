package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.layout.Size
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
            Stack() {
                expanded {
                    DealCardImage(
                        mainImageRes = deal.mainImageResId,
                        partnerImageRes = deal.partnerLogoResId
                    )
                }
                aligned(Alignment.BottomRight) {
                    DealPrice(deal.price)
                }
            }

            DealNames(deal.title, deal.subtitle, deal.isFavourite)
            DealSellingPoints(deal.usps)
            DealSearchTags(deal.searchTags)
        }
    }
}

@Composable
fun DealCardImage(mainImageRes: Int, partnerImageRes: Int) {
    val mainImage = +imageResource(mainImageRes)

    Stack(modifier = Spacing(left = 16.dp, top = 16.dp, right = 16.dp, bottom = 4.dp)) {
        expanded {
            Container(modifier = AspectRatio(1.5f)) {
                Clip(shape = RoundedCornerShape(4.dp)) {
                    DrawImage(image = mainImage)
                }
            }
        }

        aligned(Alignment.TopLeft) {
            PartnerLogoImage(partnerImageRes)
        }
    }
}

@Composable
fun PartnerLogoImage(partnerImageRes: Int) {
    val partnerLogo = if (partnerImageRes > 0) +imageResource(partnerImageRes) else null
    partnerLogo?.let {
        val inputWidth = partnerLogo.width.toFloat()
        val inputHeight = partnerLogo.height.toFloat()
        val inputAspectRatio = inputWidth / inputHeight

        val height = 48.dp
        val width = height * inputAspectRatio

        Container(
            modifier = Size(
                width = width,
                height = height
            ) wraps Spacing(left = 16.dp, top = 16.dp)
        ) {
            DrawImage(image = partnerLogo)
        }
    }
}

@Composable
fun DealPrice(price: DealPrice) {
    val oldPriceTextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        decoration = TextDecoration.LineThrough,
        color = Color.White
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
        modifier = Spacing(right = 12.dp)
    ) {
        Row(
            modifier = Spacing(left = 16.dp, right = 16.dp, top = 8.dp, bottom = 8.dp),
            arrangement = Arrangement.Center
        ) {
            Text(
                price.oldPrice,
                style = oldPriceTextStyle,
                modifier = Spacing(right = 4.dp, top = 3.dp)
            )
            Text(price.newPrice, style = newPriceTextStyle, modifier = Spacing(left = 4.dp))
        }
    }
}

@Composable
fun DealNames(title: String, subtitle: String, isFavourite: Boolean) {
    val titleStyle = ((+MaterialTheme.typography()).subtitle1)
    val subTitleStyle = ((+MaterialTheme.typography()).subtitle2)

    FlexRow(crossAxisSize = LayoutSize.Expand, modifier = Spacing(left = 16.dp, right = 4.dp)) {

        expanded(flex = 1f) {
            Column(modifier = Spacing(top = 11.dp)) {
                Text(title, style = titleStyle)
                Text(
                    subtitle,
                    style = subTitleStyle,
                    modifier = Spacing(top = 2.dp, bottom = 4.dp)
                )
            }
        }

        inflexible {
            DealFavourite(isFavourite)
        }
    }
}

@Composable
fun DealFavourite(isFavourite: Boolean) {
    val iconRes = if (isFavourite) {
        R.drawable.ic_baseline_favorite_24
    } else {
        R.drawable.ic_baseline_favorite_border_24
    }
    val favIcon = +vectorResource(iconRes)

    Ripple(bounded = true) {
        Padding(padding = 12.dp) {
            Container(modifier = Size(width = 24.dp, height = 24.dp)) {
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
        modifier = Spacing(
            left = 8.dp,
            right = 8.dp,
            top = 4.dp,
            bottom = 8.dp
        ) wraps ExpandedWidth
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
    Row(modifier = Spacing(left = 8.dp, right = 8.dp)) {
        val icon = +vectorResource(R.drawable.ic_baseline_check_24)
        Container(modifier = Size(width = 18.dp, height = 18.dp)) {
            DrawVector(vectorImage = icon)
        }
        Text(
            "$amount köpta",
            modifier = Spacing(left = 8.dp),
            style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif)
        )
    }
}

@Composable
fun DealSaveSellingPoint(amount: Int) {
    Row(modifier = Spacing(left = 8.dp, right = 8.dp)) {
        val icon = +vectorResource(R.drawable.ic_baseline_new_releases_24)
        Container(modifier = Size(width = 18.dp, height = 18.dp)) {
            DrawVector(vectorImage = icon)
        }
        Text(
            "Spara $amount%",
            modifier = Spacing(left = 8.dp),
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
        modifier = Spacing(
            left = 8.dp,
            right = 8.dp,
            top = 8.dp,
            bottom = 8.dp
        ) wraps ExpandedWidth
    ) {
        searchTags.forEach { DealSearchTag(it) }
    }
}

@Composable
fun DealSearchTag(name: String) {
    Surface(
        border = Border(color = Color(0xb3000000), width = 1.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Spacing(left = 4.dp, right = 4.dp)
    ) {
        Ripple(bounded = true) {
            Padding(left = 8.dp, right = 8.dp, top = 7.dp, bottom = 7.dp) {
                Row() {
                    VectorImage(
                        R.drawable.ic_baseline_search_24,
                        Size(width = 18.dp, height = 18.dp)
                    )
                    Text(
                        name,
                        style = TextStyle(fontSize = 14.sp, fontFamily = FontFamily.SansSerif),
                        modifier = Spacing(left = 4.dp, right = 4.dp, bottom = 1.dp)
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
