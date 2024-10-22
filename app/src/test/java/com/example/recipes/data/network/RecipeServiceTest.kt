package com.example.recipes.data.network

import com.example.recipes.data.model.RecipeModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RecipeServiceTest {

    private lateinit var recipeService: RecipeService

    @RelaxedMockK
    private lateinit var apiMock: RecipeApiClient

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipeService = RecipeService(apiMock)
    }

    @Test
    fun `When get recipe list is called, Given a right api body, Then the response should be the recipe list`() = runBlocking {
        val recipeList = listOf(RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0)))

        //Given
        coEvery {
            apiMock.getAllRecipes().body()
        } returns recipeList

        //When
        val response = recipeService.getRecipeList()

        //Then
        assert(recipeList == response)
    }

    @Test
    fun `When get recipe list is called, Given a null api body, Then the response should be an empty recipe list`() = runBlocking {
        //Given
        coEvery {
            apiMock.getAllRecipes().body()
        } returns null

        //When
        val response = recipeService.getRecipeList()

        //Then
        assert(response.isEmpty())
    }
}