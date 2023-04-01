package fr.bankin.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ResourceList(
    @Json(name = "resources")
    val resources: List<Resource>,
    @Json(name = "pagination")
    val pagination: Pagination
)
