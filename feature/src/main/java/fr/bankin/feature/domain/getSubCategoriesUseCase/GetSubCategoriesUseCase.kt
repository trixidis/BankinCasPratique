package fr.bankin.feature.domain.getSubCategoriesUseCase

import fr.bankin.feature.data.local.SubCategory
import kotlinx.coroutines.flow.Flow

interface GetSubCategoriesUseCase {

    suspend fun invoke(id:Int): Flow<List<SubCategory>>
}