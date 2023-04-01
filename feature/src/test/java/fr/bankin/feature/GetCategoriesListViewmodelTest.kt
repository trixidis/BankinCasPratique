package fr.bankin.feature

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCase
import fr.bankin.feature.presentation.categoriesList.CategoriesListViewModel
import fr.bankin.feature.presentation.states.ListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class GetCategoriesListViewmodelTest {
    val vm = CategoriesListViewModel(
        object : GetCategoriesUseCase {
            override suspend fun invoke(): Flow<List<ParentCategory>> {
                return flow {
                    emit(emptyList())
                }
            }
        }
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        val testScheduler = TestCoroutineScheduler()
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
    }



    @Test
    fun testUiStateIsLoadingOnStart(){
        assert(vm.uiState.value == ListUiState.Loading)
    }

    @Test
    fun testListCategoryGotResultsBack(){
        assert(vm.uiState.value is ListUiState.Loading)
        vm.getCategories()
        assert(vm.uiState.value is ListUiState.CategoriesRetrieved)

    }

    @Test
    fun testListCategoryResultsEmpty(){
        vm.getCategories()
        assert((vm.uiState.value as ListUiState.CategoriesRetrieved).categories.isEmpty())
    }
}