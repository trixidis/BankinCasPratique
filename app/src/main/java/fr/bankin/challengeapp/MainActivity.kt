package fr.bankin.challengeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import fr.bankin.feature.presentation.ui.CategoriesScreen
import fr.bankin.feature.presentation.ui.theme.ChallengeAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeAppTheme {
                // A surface container using the 'background' color from the theme
                CategoriesScreen()
            }
        }
    }
}
