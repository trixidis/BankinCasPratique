package fr.bankin.feature

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.data.local.SubCategory
import fr.bankin.feature.data.repository.FakeRepository
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCase
import fr.bankin.feature.domain.getCategoriesUseCase.GetCategoriesUseCaseImpl
import fr.bankin.feature.domain.getSubCategoriesUseCase.GetSubCategoriesUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCategoriesUseCaseTest {

    private val usecase = GetCategoriesUseCaseImpl(FakeRepository())
    private val usecaseSubCategories = GetSubCategoriesUseCaseImpl(FakeRepository())

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun askForCategoriesNotEmpty()= runTest{
        val values = mutableListOf<List<ParentCategory>>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            usecase.invoke().toList(values)
        }

        assert(values.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun askForSubCategoriesNotEmpty()= runTest{
        val values = mutableListOf<List<SubCategory>>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            usecaseSubCategories.invoke(0).toList(values)
        }

        assert(values.isNotEmpty())
    }
}