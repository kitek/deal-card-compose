package pl.kitek.dealcard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import pl.kitek.dealcard.ui.home.HomeScreen
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())


        setContent {
            MaterialTheme(
                colors = lightThemeColors,
                typography = themeTypography
            ) {
                HomeScreen()
            }

        }
    }
}


@Preview
@Composable
fun DefaultPreview() {
    HomeScreen()
}
