package fr.bankin.feature.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parent_category")
data class ParentCategory(
    val resource_uri:String?,
    val resource_type:String?,
    val name:String,
    @PrimaryKey
    val id:Int,
)