package fr.bankin.feature.data.repository

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.data.local.SubCategory

class FakeRepository : IRepository {
    override suspend fun getCategories(): List<ParentCategory> =
        listOf(ParentCategory("","","",0))

    override suspend fun getSubCategories(id: Int): List<SubCategory> =
        listOf(SubCategory("",0))
}