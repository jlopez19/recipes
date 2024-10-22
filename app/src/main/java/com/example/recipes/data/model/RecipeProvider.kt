package com.example.recipes.data.model

import javax.inject.Inject

class RecipeProvider @Inject constructor() {
    var recipeList = emptyList<RecipeModel>()
}