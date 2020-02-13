package pl.kitek.dealcard.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.res.vectorResource

@Composable
fun VectorImage(
    @DrawableRes resId: Int,
    modifier: Modifier
) {
    val icon = +vectorResource(resId)
    Container(modifier = modifier) {
        DrawVector(vectorImage = icon)
    }
}
