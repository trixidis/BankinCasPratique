package fr.bankin.feature.presentation.subCategoriesList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.bankin.feature.domain.getSubCategoriesUseCase.GetSubCategoriesUseCase
import fr.bankin.feature.presentation.states.ListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoriesListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCaseSubCategories: GetSubCategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val categoryId: Int = checkNotNull(savedStateHandle["categoryId"])

    fun getSubCategories() {
        Log.d("SHOW", "on va montrer les subcategories de $categoryId")
        viewModelScope.launch {
            useCaseSubCategories.invoke(categoryId)
                .catch {
                    _uiState.value = ListUiState.Failed
                }
                .collect {
                    _uiState.value = ListUiState.SubCategoriesRetrieved(it)
                }
        }


    }
}
