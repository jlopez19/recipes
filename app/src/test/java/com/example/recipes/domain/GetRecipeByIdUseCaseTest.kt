package com.example.recipes.domain

import com.example.recipes.data.model.RecipeModel
import com.example.recipes.data.repository.RecipeRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class GetRecipeByIdUseCaseTest {

    private lateinit var getRecipeByIdUseCase: GetRecipeByIdUseCase

    @RelaxedMockK
    private lateinit var repositoryMock: RecipeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRecipeByIdUseCase = GetRecipeByIdUseCase(repositoryMock)
    }

    @Test
    fun `When get recipe by ID is called, Given a right recipe, Then the response should be the recipe`() {
        val recipe = RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0))

        //Given
        every {
            repositoryMock.getRecipeById(any())
        } returns recipe

        //When
        val response = getRecipeByIdUseCase(1)

        //Then
        assert(recipe == response)
    }
}