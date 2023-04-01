package fr.bankin.feature.data.network

import fr.bankin.feature.data.model.ResourceList
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("bankin-engineering/challenge-android/master/categories.json")
    suspend fun getCategories(): Response<ResourceList>

}