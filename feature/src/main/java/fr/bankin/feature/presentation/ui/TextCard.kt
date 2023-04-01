package fr.bankin.feature.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.bankin.feature.data.local.ParentCategory

@Composable
fun TextCard(
    text: String
    ,modifier : Modifier = Modifier.fillMaxWidth()
) {
    Card(
        modifier
            .padding(16.dp)
        ,
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
        }
    }
}
