package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import pl.kitek.dealcard.R
import pl.kitek.dealcard.data.models
import pl.kitek.dealcard.model.DealModel

@Composable
fun HomeScreen() {
    Column {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        VerticalScroller(modifier = LayoutFlexible(1f)) {
            Column {
                HomeVerticalDeals(models)
            }
        }
    }
}

@Composable
fun HomeVerticalDeals(models: List<DealModel>) {
    models.forEach { DealCard(it) }
}
