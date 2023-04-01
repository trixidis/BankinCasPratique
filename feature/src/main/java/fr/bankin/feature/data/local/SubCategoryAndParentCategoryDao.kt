package fr.bankin.feature.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SubCategoryAndParentCategoryDao {
    @Query(
        "SELECT * " +
                "FROM parent_category,subcategory " +
                "WHERE parent_category.id = subcategory.parentId " +
                "AND parent_category.id = :id"
    )
    fun getSubcategoriesForCategory(id:Int):List<SubCategory>
}