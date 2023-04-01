package fr.bankin.feature.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.bankin.feature.R
import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.presentation.categoriesList.CategoriesListViewModel
import fr.bankin.feature.presentation.states.ListUiState
import fr.bankin.feature.presentation.ui.GetDataFailView
import fr.bankin.feature.presentation.ui.LoadingView
import fr.bankin.feature.presentation.ui.TextCard
import fr.bankin.feature.presentation.ui.theme.ChallengeAppTheme


@Composable
fun CategoriesList(
    onNavigateToSubCategoriesList: (id: Int) -> Unit,
    vm: CategoriesListViewModel = hiltViewModel()
) {
    val uiState = vm.uiState.collectAsState()
    val listState = rememberLazyListState()

    val configuration = LocalConfiguration.current

    val savedScrollPosition = remember(configuration) {
        mutableStateOf(0)
    }

    LaunchedEffect(remember { derivedStateOf { listState.firstVisibleItemIndex } }) {
        savedScrollPosition.value = listState.firstVisibleItemIndex
    }

    LaunchedEffect(configuration) {
        listState.scrollToItem(savedScrollPosition.value)
    }

    LaunchedEffect(key1 = Unit, block = {
        vm.getCategories()
    })


    Surface(modifier = Modifier.fillMaxSize()) {
        when (uiState.value) {
            is ListUiState.CategoriesRetrieved -> {
                val state = uiState.value as ListUiState.CategoriesRetrieved

                LazyColumn(state = listState) {
                    items(state.categories) {
                        TextCard(modifier = Modifier.clickable { onNavigateToSubCategoriesList(it.id) }, text= it.name)
                    }
                }
            }
            ListUiState.Loading -> {
                LoadingView()
            }
            ListUiState.Failed -> GetDataFailView()
            else -> {

            }
        }
    }

}

