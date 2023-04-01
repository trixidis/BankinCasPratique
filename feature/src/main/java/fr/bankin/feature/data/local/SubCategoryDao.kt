package fr.bankin.feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubCategoryDao {

    @Insert
    fun insertAll(subCategories : List<SubCategory>)

    @Insert
    fun insert(subCategory : SubCategory)

    @Delete
    fun delete(subCategory: SubCategory)

    @Query("SELECT * FROM subcategory")
    fun getAll():List<SubCategory>
}