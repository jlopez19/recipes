package com.example.recipes.domain

import com.example.recipes.data.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(recipeId: Int) = repository.getRecipeById(recipeId)
}