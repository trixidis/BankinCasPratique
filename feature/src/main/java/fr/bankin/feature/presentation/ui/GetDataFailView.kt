package fr.bankin.feature.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import fr.bankin.feature.R

@Composable
fun GetDataFailView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
            Text(
                text = stringResource(id = R.string.no_content),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
    }
}