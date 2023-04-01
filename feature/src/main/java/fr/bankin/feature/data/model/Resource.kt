package fr.bankin.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Resource(
    @Json(name = "id")
    val id: Int,
    @Json(name = "resource_uri")
    val resourceUri: String,
    @Json(name = "resource_type")
    val resourceType: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "parent")
    val parent: Parent?,
    @Json(name = "custom")
    val custom: Boolean,
    @Json(name = "other")
    val other: Boolean,
    @Json(name = "is_deleted")
    val isDeleted: Boolean
)