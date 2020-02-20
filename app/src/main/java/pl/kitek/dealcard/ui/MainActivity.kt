package pl.kitek.dealcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import pl.kitek.dealcard.ui.home.HomeScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeScreen()
        }
    }
}




//@Preview(name = "Begin")
//@Composable
//fun RowBeginPreview() {
//    SearchRow(Arrangement.Begin)
//}
//
//@Preview(name = "Center")
//@Composable
//fun RowCenterPreview() {
//    SearchRow(Arrangement.Center)
//}
//
//@Preview(name = "End")
//@Composable
//fun RowEndPreview() {
//    SearchRow(Arrangement.End)
//}
//
//@Preview(name = "SpaceEvenly")
//@Composable
//fun RowEvenlyPreview() {
//    SearchRow(Arrangement.SpaceEvenly)
//}
//
//@Preview(name = "SpaceBetween")
//@Composable
//fun RowSpaceBetweenPreview() {
//    SearchRow(Arrangement.SpaceBetween)
//}
//
//@Preview(name = "SpaceAround")
//@Composable
//fun RowSpaceAroundPreview() {
//    SearchRow(Arrangement.SpaceAround)
//}

//@Preview
//@Composable
//fun SearchRow() {
//    Row(
//        modifier = LayoutWidth.Fill + LayoutPadding(8.dp)
//    ) {
//        DealSearchTag("One")
//        DealSearchTag("Two")
//        DealSearchTag("Three", modifier = LayoutFlexible(0.5f))
//    }
//}

//@Preview
//@Composable
//fun ColumnPreview() {
//
//    Column(
//        modifier = LayoutPadding(8.dp) + LayoutHeight.Constrain(minHeight = 200.dp, maxHeight = 200.dp),
//        arrangement = Arrangement.Begin
//    ) {
//        DealSearchTag("One")
//        DealSearchTag("Two")
//        DealSearchTag("Three")
//    }
//
//}


// LayoutPadding
// LayoutHeight / LayoutWidth
// LayoutGravity
// LayoutFlexible
// LayoutSize

// combining them


//@Preview
//@Composable
//fun StackPreview() {
//    Stack {
//        BackgroundImage()
//        PriceText(
//                text = "200 kr",
//                modifier = LayoutGravity.BottomLeft
//        )
//        Button(
//                text = "Buy",
//                modifier = LayoutGravity.BottomRight
//        )
//        Text(
//                text = "Hello world",
//                modifier = LayoutGravity.Center
//        )
//    }
//}


//@Preview
//@Composable
//fun LayoutPaddingPreview() {
//
//    PriceText(text = "Buy me", modifier = LayoutPadding(10.dp))
//
//}

@Composable
fun PriceText(text: String, modifier: Modifier = Modifier.None) {

    LayoutPadding(16.dp)
    LayoutPadding(
            left = 16.dp,
            top = 8.dp,
            right = 16.dp,
            bottom = 8.dp
    )

    Text(
            text = "Hello",
            modifier = LayoutPadding(8.dp)
    )


    LayoutWidth.Fill
    LayoutWidth.Min(100.dp)
    LayoutWidth.Max(100.dp)
    LayoutWidth.Constrain(
            minWidth = 100.dp,
            maxWidth = 200.dp
    )
    LayoutSize(
            width = 24.dp,
            height = 24.dp
    )

    Stack {
        LayoutGravity.TopLeft
        LayoutGravity.TopCenter
        LayoutGravity.TopRight
        LayoutGravity.CenterLeft
        LayoutGravity.Center
        // ....
    }


    Row {
        LayoutFlexible(1f)
        LayoutFlexible(2f)
    }






    Surface(color = Color.Red, modifier = LayoutWidth.Min(200.dp)) {
        Text(
                text = text,
                style = TextStyle(color = Color.White, fontSize = 18.sp),
                modifier = LayoutPadding(16.dp)
        )
    }
}


@Composable
fun BackgroundImage() {
    Surface(color = Color.LightGray) {
        Container(width = 300.dp, height = 200.dp) {

        }
    }
}


//@Preview
//@Composable
//fun HomeScreen() {
//    MaterialTheme {
//        Column {
//            TopAppBar(title = { Text("My Application") })
//            Text("Hello world")
//        }
//    }
//}


//@Preview(group = "Group 1")
//@Composable
//fun Preview() {
//    MaterialTheme {
//        Surface(color = Color.White) {
//            Column(modifier = LayoutWidth.Fill + LayoutHeight.Fill) {
//                TopAppBar(title = { Text("My Application") })
//                Text("Hello world")
//            }
//        }
//    }
//}
