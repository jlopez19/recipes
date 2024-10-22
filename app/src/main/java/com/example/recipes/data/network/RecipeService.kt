package com.example.recipes.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(
    private val api: RecipeApiClient
) {
    suspend fun getRecipeList() = withContext(Dispatchers.IO) {
        try {
            api.getAllRecipes().body() ?: emptyList()
        } catch (_: Exception) {
            emptyList()
        }
    }
}