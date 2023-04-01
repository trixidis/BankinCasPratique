package fr.bankin.feature.domain.getSubCategoriesUseCase

import fr.bankin.feature.data.local.SubCategory
import fr.bankin.feature.data.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetSubCategoriesUseCaseImpl @Inject constructor(private val repo : IRepository):GetSubCategoriesUseCase {
    override suspend fun invoke(id: Int): Flow<List<SubCategory>> =
        flow {
            emit(repo.getSubCategories(id))
        }
}