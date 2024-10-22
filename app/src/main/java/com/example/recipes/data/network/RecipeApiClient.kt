package com.example.recipes.data.network

import com.example.recipes.data.model.RecipeModel
import retrofit2.Response
import retrofit2.http.GET

fun interface RecipeApiClient {

    @GET("recipes")
    suspend fun getAllRecipes(): Response<List<RecipeModel>>
}