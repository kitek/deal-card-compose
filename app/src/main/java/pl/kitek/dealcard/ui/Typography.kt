package pl.kitek.dealcard.ui

import androidx.ui.graphics.Color
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.sp

val dealCardFontFamily = FontFamily(
    fonts = listOf(Font("roboto_slab_bold.ttf", FontWeight.Bold))
)
val colorTextPrimary = Color(0xde000000)
val themeTypography = Typography(
    subtitle1 = TextStyle(
        color = colorTextPrimary,
        fontSize = 20.sp,
        fontFamily = dealCardFontFamily,
        fontWeight = FontWeight.Bold
    ),
    subtitle2 = TextStyle(
        color = colorTextPrimary,
        fontSize = 14.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal
    )
)
