package fr.bankin.feature.domain.getCategoriesUseCase

import fr.bankin.feature.data.local.ParentCategory
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase {
    suspend fun invoke(): Flow<List<ParentCategory>>

}