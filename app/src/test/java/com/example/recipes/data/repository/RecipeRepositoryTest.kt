package com.example.recipes.data.repository

import com.example.recipes.data.model.RecipeModel
import com.example.recipes.data.model.RecipeProvider
import com.example.recipes.data.network.RecipeService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecipeRepositoryTest {

    private lateinit var recipeRepository: RecipeRepository

    @RelaxedMockK
    private lateinit var recipeServiceMock: RecipeService

    @MockK
    private lateinit var recipeProviderMock: RecipeProvider

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipeProviderMock = RecipeProvider()
        recipeRepository = RecipeRepository(recipeServiceMock, recipeProviderMock)
    }

    @Test
    fun `When get all recipes is called, Given a right recipe list, Then the response should be the recipe list`() = runBlocking {
        val recipeList = listOf(RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0)))

        //Given
        coEvery {
            recipeServiceMock.getRecipeList()
        } returns recipeList

        //When
        val response = recipeRepository.getAllRecipes()

        //Then
        assert(recipeList == response)
    }

    @Test
    fun `When get recipe by ID is called, Given a right recipe list, Then the response should be the recipe`() {
        //Given
        recipeProviderMock.recipeList = listOf(RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0)))

        //When
        val response = recipeRepository.getRecipeById(1)

        //Then
        assert(recipeProviderMock.recipeList.first() == response)
    }
}