package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.ui.core.Clip
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
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
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import pl.kitek.dealcard.R
import pl.kitek.dealcard.model.*
import pl.kitek.dealcard.ui.VectorImage
import pl.kitek.dealcard.ui.dealCardFontFamily

@Composable
fun DealCard(model: DealModel) {
    Ripple(bounded = true) {
        Surface(color = Color.White) {
            Column {
                Stack {
                    DealCardImage(
                        mainImageRes = model.basicDeal.mainImageResId,
                        partnerImageRes = model.basicDeal.partnerLogoResId
                    )
                    DealPrice(model.basicDeal.price, modifier = LayoutGravity.BottomRight)
                }
                DealNamesRow(
                    model.basicDeal.title,
                    model.basicDeal.subtitle,
                    model.isFavourite
                ) {
                    model.toggleFavourite()
                }
                DealSellingPoints(model.basicDeal.usps)
                DealSearchTags(model.basicDeal.searchTags)
            }
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
fun DealNamesRow(
    title: String,
    subtitle: String,
    isFavourite: Boolean,
    favouriteOnClick: () -> Unit
) {
    val titleStyle = ((MaterialTheme.typography()).subtitle1)
    val subTitleStyle = ((MaterialTheme.typography()).subtitle2)

    Surface(color = Color.White) {

        Row(modifier = LayoutPadding(left = 16.dp, right = 4.dp) + LayoutWidth.Fill) {

            Column(modifier = LayoutPadding(top = 12.dp) + LayoutFlexible(1f)) {

                Text(title, style = titleStyle)
                Text(
                    subtitle,
                    style = subTitleStyle,
                    modifier = LayoutPadding(top = 2.dp, bottom = 4.dp)
                )
            }
            DealFavouriteButton(isFavourite, favouriteOnClick)
        }
    }
}


@Composable
fun DealFavouriteButton(isFavourite: Boolean, onClick: () -> Unit) {
    Ripple(bounded = true) {
        Clickable(onClick = onClick) {
            Padding(padding = 12.dp) {
                Container(modifier = LayoutSize(width = 24.dp, height = 24.dp)) {
                    val iconRes = if (isFavourite) {
                        R.drawable.ic_baseline_favorite_24
                    } else {
                        R.drawable.ic_baseline_favorite_border_24
                    }
                    val favIcon = vectorResource(iconRes)
                    DrawVector(vectorImage = favIcon)
                }
            }
        }
    }
}

@Composable
fun DealSellingPoints(usps: List<UniqueSellingPoint>) {
    if (usps.isEmpty()) return

    Surface(color = Color.White) {
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
fun DealSearchTag(name: String, modifier: Modifier = Modifier.None) {
    Surface(
        borderWidth = 1.dp,
        borderBrush = SolidColor(Color(0xb3000000)),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier + LayoutPadding(left = 4.dp, right = 4.dp)
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


//@Preview(
//    name = "Full Deal Card",
//    group = "Top level components",
//    widthDp = 200,
//    heightDp = 600
//)
//@Composable
//fun DefaultPreview() {
//    DealCard(model = DealModel(deal2))
//}
//
//@Preview(
//    name = "Deal names with favourite",
//    group = "Subcomponents"
//)
//@Composable
//fun DealNamesPreview() {
//    DealNamesRow(deal3.title, deal3.subtitle, true) {}
//}

//@Preview(name = "Deal selling points")
//@Composable
//fun DealSellingPointsPreview() {
//    DealSellingPoints(deal1.usps)
//}





































