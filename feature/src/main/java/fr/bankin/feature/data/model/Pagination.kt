package fr.bankin.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pagination(
    @Json(name = "previous_uri")
    val previousUri: String?,
    @Json(name = "next_uri")
    val nextUri: String?
)