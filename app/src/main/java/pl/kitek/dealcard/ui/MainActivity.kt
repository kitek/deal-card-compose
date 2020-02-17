package pl.kitek.dealcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutWidth
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview

    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContent {
                MaterialTheme {
                    Column {
                        TopAppBar(title = { Text("My Application") })
                        Text("Hello world")
                    }
                }
            }
        }
    }




@Preview
@Composable
fun Preview() {
    MaterialTheme {
        Surface(color = Color.White) {
            Column(modifier = LayoutWidth.Fill + LayoutHeight.Fill) {
                TopAppBar(title = { Text("My Application") })
                Text("Hello world")
            }
        }
    }
}
