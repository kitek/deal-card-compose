package pl.kitek.dealcard.ui.home

import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.FloatingActionButton
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import pl.kitek.dealcard.R
import pl.kitek.dealcard.model.DealModel

@Model
class CartModel(var items: List<CartItem>)

val cart = CartModel(emptyList())

@Composable
fun HomeScreen() {
    Column {
        TopAppBar(title = { Text(stringResource(R.string.app_name)) })
        Stack {
            Cart(model = cart)
            AddToCartButton(
                    model = cart,
                    modifier = LayoutGravity.BottomRight
            )
        }
    }
}


data class CartItem(val name: String, val price: String)


@Composable
fun Cart(model: CartModel) {
    VerticalScroller {
        Column {
            model.items.forEach { item -> CartRow(item) }
            if (model.items.isEmpty()) EmptyCartText()
        }
    }
}

@Composable
fun AddToCartButton(
        model: CartModel,
        modifier: Modifier = Modifier.None
) {
    FloatingActionButton(
            text = "+",
            modifier = modifier + LayoutPadding(16.dp),
            onClick = {
                model.items += CartItem(
                        name = "Item ${model.items.size + 1}",
                        price = "100 $"
                )
            }
    )
}

@Composable
fun EmptyCartText() {
    Center {
        Text(
                text = "Cart is empty",
                style = TextStyle(fontSize = 18.sp),
                modifier = LayoutPadding(16.dp)

        )
    }
}

@Composable
fun CartRow(cartItem: CartItem) {
    Row(
            modifier = LayoutWidth.Fill + LayoutPadding(16.dp),
            arrangement = Arrangement.SpaceBetween
    ) {
        Text(text = cartItem.name, style = TextStyle(fontSize = 14.sp))
        Text(text = cartItem.price, style = TextStyle(fontSize = 14.sp))
    }
}


@Composable
fun HomeVerticalDeals(models: List<DealModel>) {
    models.forEach { DealCard(it) }
}

@Composable
fun AwesomeCounter() {
    val counter = state { 0 }

    Row {
        Text(text = "Current value: ${counter.value}")
        Button(text = "Increment", onClick = {
            counter.value += 1
        })
    }
}
