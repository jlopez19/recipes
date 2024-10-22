package com.example.recipes.domain

import com.example.recipes.data.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAllRecipes()
}