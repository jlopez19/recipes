package com.example.recipes.domain

import com.example.recipes.data.model.RecipeModel
import com.example.recipes.data.repository.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest {

    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @RelaxedMockK
    private lateinit var repositoryMock: RecipeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRecipesUseCase = GetRecipesUseCase(repositoryMock)
    }

    @Test
    fun `When get all recipes is called, Given a right recipe list, Then the response should be the recipe list`() = runBlocking {
        val recipeList = listOf(RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0)))

        //Given
        coEvery {
            repositoryMock.getAllRecipes()
        } returns recipeList

        //When
        val response = getRecipesUseCase()

        //Then
        assert(recipeList == response)
    }
}