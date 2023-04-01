package fr.bankin.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Parent(
    @Json(name = "id")
    val id: Int,
    @Json(name = "resource_uri")
    val resourceUri: String,
    @Json(name = "resource_type")
    val resourceType: String
)