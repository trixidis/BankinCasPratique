package fr.bankin.feature.presentation.categoriesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCase
import fr.bankin.feature.presentation.states.ListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(val useCase: GetCategoriesUseCase) : ViewModel() {

    private val _uiState  = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getCategories(){
        viewModelScope.launch {
            useCase.invoke()
                .catch {
                    _uiState.value = ListUiState.Failed
                }
                .collect{
                    _uiState.value = ListUiState.CategoriesRetrieved(it)
                }
        }
    }

}