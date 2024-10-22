package com.example.recipes.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.model.RecipeModel
import com.example.recipes.domain.GetRecipeByIdUseCase
import com.example.recipes.domain.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
): ViewModel() {

    val recipeModelList = MutableLiveData<List<RecipeModel>>()
    val recipeModel = MutableLiveData<RecipeModel>()

    fun getRecipes() {
        viewModelScope.launch {
            recipeModelList.postValue(getRecipesUseCase())
        }
    }

    fun getRecipeById(recipeId: Int) {
        getRecipeByIdUseCase(recipeId)?.let { saveRecipe ->
            recipeModel.postValue(saveRecipe)
        }
    }
}