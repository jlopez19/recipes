package com.example.recipes.data.repository

import com.example.recipes.data.model.RecipeModel
import com.example.recipes.data.model.RecipeProvider
import com.example.recipes.data.network.RecipeService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeProvider: RecipeProvider
) {
    suspend fun getAllRecipes(): List<RecipeModel> {
        val response = recipeService.getRecipeList()
        recipeProvider.recipeList = response

        return response
    }

    fun getRecipeById(recipeId: Int) = recipeProvider.recipeList.find { it.id == recipeId }
}