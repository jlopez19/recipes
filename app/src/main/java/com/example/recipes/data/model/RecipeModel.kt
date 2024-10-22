package com.example.recipes.data.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val location: DoubleArray
)