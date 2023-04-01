package fr.bankin.feature.domain.getCategoriesUseCase

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.data.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(private val repo: IRepository) :
    GetCategoriesUseCase {
    override suspend fun invoke(): Flow<List<ParentCategory>> =
        flow {
            emit(repo.getCategories())
        }
}