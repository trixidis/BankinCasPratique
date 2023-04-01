package fr.bankin.feature.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.bankin.feature.R
import fr.bankin.feature.presentation.states.ListUiState
import fr.bankin.feature.presentation.subCategoriesList.SubCategoriesListViewModel

@Composable
fun SubCategoriesList(vm: SubCategoriesListViewModel = hiltViewModel()) {
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
        vm.getSubCategories()
    })

    Surface(modifier = Modifier.fillMaxSize()) {
        when (uiState.value) {
            is ListUiState.SubCategoriesRetrieved -> {
                val state = uiState.value as ListUiState.SubCategoriesRetrieved

                LazyColumn(state = listState) {
                    items(state.subCategories) {
                        TextCard(it.name)
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


