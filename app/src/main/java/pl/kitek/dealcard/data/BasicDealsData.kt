package pl.kitek.dealcard.data

import pl.kitek.dealcard.R
import pl.kitek.dealcard.model.*

val deal1 = BasicDeal(
    id = "1jec7thuseq4",
    title = "GlamWhite tandblekning hemma",
    subtitle = "Fungerar även på känsliga tänder",
    mainImageResId = R.drawable.deal_image_1,
    price = DealPrice(oldPrice = "949 kr", newPrice = "149 kr"),
    usps = listOf(BoughtUsp("4000+"), SaveUsp(84)),
    searchTags = listOf("glamwhite", "skönhetsprodukter", "tandvård")
)
val deal2 = BasicDeal(
    id = "m2ec70h44t2x",
    title = "Postkodlotteriet: Första månaden 1 kr och varannan månad i ett halvår",
    subtitle = "Ordinarie pris 170 kr per månad",
    mainImageResId = R.drawable.deal_image_2,
    partnerLogoResId = R.drawable.deal_image_2_partner,
    price = DealPrice(oldPrice = "1 199 kr", newPrice = "259 kr"),
    searchTags = listOf("hobby", "spel")
)
val deal3 = BasicDeal(
    id = "4ke4nzy4gkx5",
    title = "3-rätters middag för 2 personer",
    subtitle = "Hos Bistro Queens på Adelgatan",
    mainImageResId = R.drawable.deal_image_3,
    partnerLogoResId = R.drawable.deal_image_3_partner,
    price = DealPrice(oldPrice = "674 kr", newPrice = "375 kr"),
    usps = listOf(BoughtUsp("10+"), SaveUsp(44)),
    searchTags = listOf("mat", "restaurang")
)
val deal4 = BasicDeal(
    id = "68ecvca40wtz",
    title = "Ansiktsrengörare i silikon",
    subtitle = "Ta din hudvårdsrutin till en ny nivå",
    mainImageResId = R.drawable.deal_image_4,
    price = DealPrice(oldPrice = "399 kr", newPrice = "149 kr"),
    usps = listOf(BoughtUsp("250+"), SaveUsp(63)),
    searchTags = listOf("hudvård", "ansikte")
)

val models = listOf(
    DealModel(deal1),
    DealModel(deal2),
    DealModel(deal3),
    DealModel(deal4)
)
