package fr.bankin.feature.presentation.states

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.data.local.SubCategory

sealed class ListUiState {
    object Loading : ListUiState()
    class CategoriesRetrieved(val categories : List<ParentCategory>) : ListUiState()
    class SubCategoriesRetrieved(val subCategories : List<SubCategory>) : ListUiState()
    object Failed : ListUiState()
}