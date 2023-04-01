package fr.bankin.feature.data.repository

import android.util.Log
import fr.bankin.feature.data.local.*
import fr.bankin.feature.data.network.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(
    private val apiService: ServiceApi,
    private val categoryDao: ParentCategoryDao,
    private val subCategoryDao: SubCategoryDao,
    private val subCategoryCategoryDao: SubCategoryAndParentCategoryDao
) :
    IRepository {

    override suspend fun getCategories(): List<ParentCategory> {
        val categories =
            withContext(Dispatchers.IO) {
                categoryDao.getAll()
            }
        return categories.ifEmpty {
                val response = apiService.getCategories()
                if (response.isSuccessful) {
                    response.body()?.let { resources ->
                        withContext(Dispatchers.IO) {
                            categoryDao.insertAll(resources.resources.filter {
                                it.parent == null
                            }.map {
                                ParentCategory(it.resourceUri, it.resourceType, it.name, it.id)
                            })
                            resources.resources.filter { it.parent != null }.map {
                                SubCategory(it.name, it.parent!!.id, it.id)
                            }.forEach {
                                try {
                                    subCategoryDao.insert(it)
                                } catch (e: Exception) {
                                    // Gère notamment le cas ou il y a deux catégories
                                    // avec le même id comme par exemple dans le json fourni
                                    // (id:309 en double)
                                    Log.e("Error", "Erreur durant le insert des sub categories", e)
                                }
                            }
                        }
                    }
                }

            withContext(Dispatchers.IO) {
                categoryDao.getAll()
            }
        }
    }

    override suspend fun getSubCategories(id: Int): List<SubCategory> {
        return withContext(Dispatchers.IO) {
            subCategoryCategoryDao.getSubcategoriesForCategory(id)
        }
    }
}