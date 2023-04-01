package fr.bankin.feature.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "subcategory",
    foreignKeys = [ForeignKey(
        entity = ParentCategory::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SubCategory(
    val name: String,
    val parentId: Int,
    @PrimaryKey
    val id: Int? = null
) {
}