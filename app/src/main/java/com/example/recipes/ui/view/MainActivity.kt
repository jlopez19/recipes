package com.example.recipes.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.data.model.RecipeModel
import com.example.recipes.databinding.ActivityMainBinding
import com.example.recipes.ui.adapter.RecipeAdapter
import com.example.recipes.ui.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recipeViewModel: RecipeViewModel by viewModels()

    private val recipeModelList = mutableListOf<RecipeModel>()

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeViewModel.getRecipes()
        recipeViewModel.recipeModelList.observe(this) { recipeModelListObserved ->
            if (recipeModelListObserved.isEmpty())
                showToast(getString(R.string.error))

            recipeModelList.addAll(recipeModelListObserved)
            initRecyclerView()
        }

        initRecipeFilter()
    }

    private fun initRecyclerView() {
        recipeAdapter = RecipeAdapter(recipeModelList) { onItemSelected(it) }

        binding.recyclerViewRecipes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recipeAdapter
        }
    }

    private fun initRecipeFilter() {
        binding.editTextFilterRecipes.addTextChangedListener { searchText ->
            val filteredRecipeList = recipeModelList.filter { recipe ->
                recipe.name.lowercase().contains(searchText.toString().lowercase())
            }

            if (filteredRecipeList.isEmpty())
                showToast(getString(R.string.not_recipes))

            recipeAdapter.setRecipeList(filteredRecipeList)
        }
    }

    private fun onItemSelected(recipeModel: RecipeModel) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra(RecipeDetailActivity.RECIPE_ID_EXTRA_NAME, recipeModel.id)
        }
        startActivity(intent)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}