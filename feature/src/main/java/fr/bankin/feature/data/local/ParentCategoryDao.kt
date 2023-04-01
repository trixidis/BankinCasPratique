package fr.bankin.feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ParentCategoryDao {
    @Insert
    fun insertAll(categories : List<ParentCategory>)

    @Insert
    fun insert(category : ParentCategory)

    @Delete
    fun delete(category: ParentCategory)

    @Query("SELECT * FROM parent_category")
    fun getAll():List<ParentCategory>
}