package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import pl.kitek.dealcard.R
import pl.kitek.dealcard.data.deals
import pl.kitek.dealcard.model.BasicDeal

@Composable
fun HomeScreen() {
    Column {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        VerticalScroller(modifier = LayoutFlexible(1f)) {
            Column {
                HomeVerticalDeals(deals)
            }
        }
    }
}

@Composable
fun HomeVerticalDeals(deals: List<BasicDeal>) {
    deals.forEach { DealCard(deal = it) }
}
