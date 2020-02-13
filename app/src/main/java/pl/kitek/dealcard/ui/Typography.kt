package pl.kitek.dealcard.ui

import androidx.compose.unaryPlus
import androidx.ui.core.sp
import androidx.ui.material.Typography
import androidx.ui.res.colorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import pl.kitek.dealcard.R

val dealCardFontFamily = FontFamily(
    fonts = listOf(Font("roboto_slab_bold.ttf", FontWeight.Bold))
)

val themeTypography = Typography(
    subtitle1 = TextStyle(
        color = +colorResource(R.color.textPrimary),
        fontSize = 20.sp,
        fontFamily = dealCardFontFamily,
        fontWeight = FontWeight.Bold
    ),
    subtitle2 = TextStyle(
        color = +colorResource(R.color.textPrimary),
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal
    )
)
