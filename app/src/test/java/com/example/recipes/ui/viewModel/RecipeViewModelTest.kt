package com.example.recipes.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipes.data.model.RecipeModel
import com.example.recipes.domain.GetRecipeByIdUseCase
import com.example.recipes.domain.GetRecipesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeViewModelTest {

    private lateinit var recipeViewModel: RecipeViewModel

    @RelaxedMockK
    private lateinit var getRecipesUseCaseMock: GetRecipesUseCase

    @RelaxedMockK
    private lateinit var getRecipeByIdUseCaseMock: GetRecipeByIdUseCase

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipeViewModel = RecipeViewModel(getRecipesUseCaseMock, getRecipeByIdUseCaseMock)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `When get recipes is called, Given a right recipe list, Then the 'Recipe Model List' should be the recipe list`() = runTest {
        val recipeList = listOf(RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0)))

        //Given
        coEvery {
            getRecipesUseCaseMock()
        } returns recipeList

        //When
        recipeViewModel.getRecipes()

        //Then
        assert(recipeViewModel.recipeModelList.value == recipeList)
    }

    @Test
    fun `When get recipe by ID is called, Given a right recipe, Then the 'Recipe Model' should be the recipe`() = runTest {
        val recipe = RecipeModel(1, "name", "description", "image", doubleArrayOf(1.0, 1.0))

        //Given
        coEvery {
            getRecipeByIdUseCaseMock(any())
        } returns recipe

        //When
        recipeViewModel.getRecipeById(1)

        //Then
        assert(recipeViewModel.recipeModel.value == recipe)
    }
}