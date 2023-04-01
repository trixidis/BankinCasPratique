package fr.bankin.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[ParentCategory::class,SubCategory::class],version=3)
abstract class AppDatabase :RoomDatabase(){
    abstract fun categoryDao():ParentCategoryDao
    abstract fun subCategoryDao():SubCategoryDao
    abstract fun subCategoryAndParentCategoryDao():SubCategoryAndParentCategoryDao
}