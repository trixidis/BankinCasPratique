package fr.bankin.feature.data.repository

import fr.bankin.feature.data.local.ParentCategory
import fr.bankin.feature.data.local.SubCategory

interface IRepository {
    suspend fun getCategories(): List<ParentCategory>
    suspend fun getSubCategories(id:Int):List<SubCategory>
}