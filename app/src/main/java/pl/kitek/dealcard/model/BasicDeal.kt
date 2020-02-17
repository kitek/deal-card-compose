package pl.kitek.dealcard.model

import androidx.compose.Model

data class BasicDeal(
    val id: String,
    val title: String,
    val subtitle: String,
    val mainImageResId: Int,
    val partnerLogoResId: Int = 0,
    val price: DealPrice,
    val usps: List<UniqueSellingPoint> = emptyList(),
    val searchTags: List<String> = emptyList()
)

data class DealPrice(val oldPrice: String, val newPrice: String)

interface UniqueSellingPoint

data class SaveUsp(val amount: Int) : UniqueSellingPoint
data class BoughtUsp(val amount: String) : UniqueSellingPoint

@Model
data class DealModel(
    val basicDeal: BasicDeal,
    var isFavourite: Boolean = false
) {

    fun toggleFavourite() {
        isFavourite = !isFavourite
    }

}
